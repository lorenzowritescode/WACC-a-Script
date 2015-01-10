var context = require("./jsTestExecution.js"),
	fs = require('fs'),
	path = require('path'),
	startDir = './js-examples',
	trim = require('./test-utils/isContaminated.js').trim,
	testStatus = {
		total: 0,
		passed: 0,
		syntax: 0,
		runtime: 0,
		wrongOutput: 0,
		dontknow: 0
	}

function testDirectory(dirpath) {
	fs.readdir(dirpath, function (err,files) {
	    if (err) throw err;

	    files.forEach(function(file) {
	    	var fullpath = dirpath + '/' + file;

	    	if (fs.statSync(fullpath).isDirectory())
	    		return testDirectory(fullpath);

	    	if (path.extname(file) != ".js")
	    		return;

	    	var absoluteFilePath = dirpath + '/' + path.basename(file, ".js"),
	    		jsFile = absoluteFilePath + ".js",
	        	inFile = absoluteFilePath + ".in",
	        	outFile = absoluteFilePath + ".out",
	        	program;

	        try {
	        	program = require(jsFile);
	        } catch (err) {
	        	return fail(jsFile, err);
	        }

	       	if (fs.existsSync(outFile)) {
	       		fs.readFile(outFile, 'utf-8', function (err, output) {
	       			var expOut = output.split('\n');

	       			if (fs.existsSync(inFile)) {
	       				fs.readFile(inFile, 'utf-8', function (err, input) {
	       					input = input.split('\n');
	       					testFile(program, expOut, input, jsFile);
	       				});
	       			} else {
	       				// There is no inputFile
	       				testFile(program, expOut, [], jsFile);
	       			}
	       		})
	       	} else {
	       		// There is no outputFile
	       		testFile(program, [], [], jsFile);
	       	}
	    });
	});
}

function testFile (program, expOut, input, path) {
	console.log(path);

	var test = new context.Test(program.program, trim(expOut), input, path),
		result = test.run(console);

	switch(result) {
		case 0:
			testStatus.passed++;
			break;
		case 1:
			testStatus.syntax++;
			break;
		case 2:
			testStatus.runtime++;
			break;
		case 3:
			testStatus.wrongOutput++;
			break;
		case 4:
			testStatus.dontknow++;
			break;
		default:
			break;
	}

	testStatus.total++;
}

function fail(path, err) {
	testStatus.total++;
	console.log("File " + path + "failed to compile due to a syntax error.", err);
}

testDirectory(startDir);
setTimeout(printResult, 1000);

function printResult() {
	console.log("TOTAL: " + testStatus.total 
		+ " | PASSED: " + testStatus.passed 
		+ " | DON'T KNOW: " + testStatus.dontknow
		+ " | SYNTAX ERROR: " + testStatus.syntax
		+ " | RUNTIME ERROR: " + testStatus.runtime)
}