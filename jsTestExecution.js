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
			onNewline: true,
			print: function(s) {
				s = s != null ? s.toString() : '';
				if (this.onNewline) {
					this.actOut.push(s);
				} else {
					var lastMessage = this.actOut.pop(),
						newMessage = lastMessage + s;

					this.actOut.push(newMessage);
				}

				this.onNewline = false;
			},
			println: function(s) {
				s = s != null ? s.toString() : '';
				if(this.onNewline) {
					this.actOut.push(s);
				} else {
					this.print(s);
					this.onNewline = true;
				}
			},
			terminate: function(exitCode) {
				this.exitStatus = exitCode || 0;
			}
		},
		run: function(printer) {
			if (typeof this.program != 'function') {
				printer.log("The 'program' field is not a function. File: " + filePath);
				return codes.syntax;
			}

			try {
				this.program(this.core);
			} catch (err) {
				printer.log("Something went wrong when executing this file: " + filePath, err);
				return codes.runtime;
			}

			var result = arraysEqual(this.expOut, this.core.actOut);

			if(result == codes.wrongOutput) {
				printer.log("Test for " + filePath + " failed.");
				printer.log("Input: \n\t" + input.join('\n\t'));
				printer.log("Output Expected: \n\t" + expOut.join('\n\t'));
				printer.log("Actual Output: \n\t" + this.core.actOut.join('\n\t'));
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

