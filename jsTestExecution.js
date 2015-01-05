var _ = require('underscore'),
	arraysEqual = require('./test-utils/isContaminated.js').arraysEqual,
	codes = require('./test-utils/isContaminated.js').codes;

function Test(programFunction, expOut, input, filePath) {
	return {
		program: programFunction,
		expOut: expOut,
		core: {
			input: input,
			index: 0,
			actOut: [],
			exitCode: 0,
			read: function(callback) {
				callback(input[this.index]);
			},
			print: function(s) {
				this.actOut.push(s);
			},
			terminate: function(exitCode) {
				this.exitStatus = exitCode || 0;
			}
		},
		run: function() {
			if (typeof this.program != 'function') {
				console.log("The 'program' field is not a function. File: " + filePath);
				return codes.syntax;
			}

			try {
				this.program(this.core);
			} catch (err) {
				console.log("Something went wrong when executing this file: " + filePath, err);
				return codes.runtime;
			}

			var result = arraysEqual(this.expOut, this.core.actOut);

			if(result == codes.wrongOutput) {
				console.log("Test for " + filePath + " failed.");
				console.log("Input: \n\t" + input.join('\n\t'));
				console.log("Output Expected: \n\t" + expOut.join('\n\t'));
				console.log("Actual Output: \n\t" + this.core.actOut.join('\n\t'));
			}

			return result;
		}
	};
}

var programFunction = function (core) {
	var x = 3;
	core.read(function (answer) {
		x = answer;
		core.print(x + 1);
	})
}

// Sample usage:
console.log(new Test(programFunction, ['#addrs#'], [4], "blah").run());

module.exports = {
	Test: Test
}

