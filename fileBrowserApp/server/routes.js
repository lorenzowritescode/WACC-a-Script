(function() {

    'use strict';
    var express = require('express');
    var router = express.Router();
    var fs = require('fs');
    var path = require('path');
    var _ = require('underscore');

    /* GET home page. */
    router.get('/', function(req, res) {
        res.render('index');
    });

    /* Serve the Tree */
    router.get('/api/tree', function(req, res) {
        var _p;
        if (req.query.id == 1) {
            _p = path.resolve(__dirname, '..', 'valid');
            processReq(_p, res);

        } else {
            if (req.query.id) {
                _p = req.query.id;
                processReq(_p, res);
            } else {
                res.json(['No valid data found']);
            }
        }
    });

    /* Serve a Resource */
    router.get('/api/resource', function(req, res) {
        res.send(fs.readFileSync(req.query.resource, 'UTF-8'));
    });

    function Printer() {
        return {
            buffer: [],
            log: function(s) {
                this.buffer.push(s);
            }
        };
    }

    function processReq(_p, res) {
        var resp = [];
        fs.readdir(_p, function(err, list) {
            for (var i = list.length - 1; i >= 0; i--) {
                var node = processNode(_p, list[i]);

                if (!node)
                    continue;
                else
                    resp.push(node);
            }
            res.json(resp);
        });
    }

    function processNode(_p, f) {
        var pathName = path.join(_p, f),
            s = fs.statSync(path.join(_p, f)),
            basename = path.basename(f),
            isJsFile = path.extname(basename) == '.js',
            isDirectory = s.isDirectory();

        if (!(isJsFile || isDirectory))
            return false;

        var testResultResponse = {},
            icon = isDirectory ? 'jstree-custom-folder' : 'jstree-custom-file';

        if (isJsFile) {
            // Once we know that it is a JS file, strip the extension.
            basename = path.basename(basename, '.js');

            // Set up for testing
            var inPath = path.join(_p, basename + '.in'),
                outPath = path.join(_p, basename + '.out'),
                hasInput = fs.existsSync(inPath),
                hasOutput = fs.existsSync(outPath),
                input = hasInput ? fs.readFileSync(inPath).toString().split('\n') : [],
                expOut = hasOutput ? fs.readFileSync(outPath).toString().split('\n') : [],
                Test = require("../../jsTestExecution.js").Test;

            // require the program function in a try catch block as to not crash the entire app.
            try{
                var programFunction = require(pathName).program,
                    printer = new Printer();
                
                expOut = _.filter(expOut, function(v) { 
                    return !(v === null || v === '');
                });

                var test = new Test(programFunction, expOut, input, pathName),
                    result = test.run(printer);

                testResultResponse = {
                    "result": result,
                    "log": printer.buffer.join('\n'),
                    "input": input.join('\n'),
                    "expOut": expOut.join('\n')
                };
                icon = pickIcon(result);
            } catch (err) {
                testResultResponse = {
                    "result": 1,
                    "log": "There was an error parsing the file.\n" + err,
                    "input": input.join('\n'),
                    "expOut": expOut.join('\n')
                };
                
                icon = pickIcon(1);
            }
        }

        return {
            "id": path.join(_p, f),
            "text": f,
            "icon": icon,
            "state": {
                "opened": false,
                "disabled": false,
                "selected": false
            },
            "source": path.join(_p, basename + '.wacc'),
            "test": testResultResponse,
            "li_attr": {
                "base": path.join(_p, f),
                "isLeaf": !isDirectory
            },
            "children": isDirectory
        };
    }

    var icons = {
        0: 'correct',
        1: 'compiler',
        2: 'compiler',
        3: 'error',
        4: 'dontknow'
    };

    function pickIcon(resultCode) {
        return icons[resultCode];
    }

    module.exports = router;

}());