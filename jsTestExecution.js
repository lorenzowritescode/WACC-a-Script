function Test(programFunction, expOut, input, filePath) {
	return {
		program: programFunction,
		expOut: expOut,
		core: {
			input: input,
			index: 0,
			actOut: [],
			read: function(callback) {
				callback(input[this.index]);
			},
			print: function(s) {
				this.actOut.push(s);
			},
			terminate: function() {}
		},
		run: function() {
			if (typeof this.program != 'function') {
				console.log("The 'program' field is not a function. File: " + filePath);
				return false;
			}

			try {
				this.program(this.core);
			} catch (err) {
				console.log("Something went wrong when executing this file: " + filePath, err);
				return false;
			}

			if(!arraysEqual(this.expOut, this.core.actOut)) {
				console.log("Test for " + filePath + " failed.");
				console.log("Input: \n\t" + input.join('\n\t'));
				console.log("Output Expected: \n\t" + expOut.join('\n\t'));
				console.log("Actual Output: \n\t" + this.core.actOut.join('\n\t'));
				return false;
			}
			console.log("[INFO] Success on execution. File: " + filePath);
			return true;
		}
	};

	function arraysEqual(a, b) {
		if (a === b) return true;
		if (a == null || b == null) return false;
		if (a.length != b.length) return false;
		for (var i = 0; i < a.length; ++i) {
			if (a[i] == '#input#' || a[i] == '#output#')
				continue;
			if (a[i] != b[i])
				return false;
		}
		return true;
	}
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

