function Pair(x, y) {
	return {fst: x, snd: y};
}

function print(s) {
	console.log(s);
}

function read(callback) {
	var readline = require('readline'),
	rl = readline.createInterface({
		input: process.stdin,
		output: process.stdout
	});

	var res;
	rl.question("", callback);
}