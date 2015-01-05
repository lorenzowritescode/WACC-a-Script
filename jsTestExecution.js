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
			var codes = {
				success: 0,
				syntax: 1,
				runtime: 2,
				wrongOutput: 3,
				dontknow: 4
			};

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

			if(result == 3) {
				console.log("Test for " + filePath + " failed.");
				console.log("Input: \n\t" + input.join('\n\t'));
				console.log("Output Expected: \n\t" + expOut.join('\n\t'));
				console.log("Actual Output: \n\t" + this.core.actOut.join('\n\t'));
			}

			return result;

			function arraysEqual(expOut, actualOut) {
				if (expOut === actualOut) 
					return codes.success;

				if (expOut.length != actualOut.length)
					return codes.wrongOutput;

				var ignores = ['#input#', '#output#', '#addrs#'],
					containsIgnores = false;

				expOut.forEach(function(s) {
					containsIgnores = containsIgnores ||
						ignores.reduce(function(previous, current, index, array) {
							return previous || (s.indexOf(current) > 0);
						}, false);
				});

				if (containsIgnores)
					return codes.dontknow;

				for (var i = 0; i < expOut.length; ++i) {
					if (expOut[i] != actualOut[i])
						return codes.wrongOutput;
				}
				return codes.success;
			}
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

// new Test(programFunction, [5], [4], "blah").run();

module.exports = {
	Test: Test
}

