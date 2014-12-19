function arraysEqual(a, b) {
	if (a === b) return true;
	if (a == null || b == null) return false;
	if (a.length != b.length) return false;
	for (var i = 0; i < a.length; ++i) {
		if (a[i] !== b[i]) return false;
	}
	return true;
}

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
			}
		},
		run: function() {
			programFunction(this.core);
			if(!arraysEqual(this.expOut, this.core.actOut)) {
				console.log("Test for " + filePath + " failed.");
				console.log("Output Expected: \n\t" + expOut.join('\n\t'));
				console.log("Actual Output: \n\t" + this.core.actOut.join('\n\t'));
			}
		}
	}
}

var programFunction = function (core) {
	var x = 3;
	core.read(function (answer) {
		x = answer;
		core.print(x + 1);
	})
}

var test1 = new Test(programFunction, [5], [6], "blah");
test1.run();

