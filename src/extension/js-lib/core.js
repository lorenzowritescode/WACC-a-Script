/* @flow */
function Pair(x :any, y :any) :Object{
	return {fst: x, snd: y};
}

function print(s :string) :void {
	console.log(s);
}

function read() {
	var readline = require('readline'),
	rl = readline.createInterface({
		input: process.stdin,
		output: process.stdout
	});

	var res;
	rl.question("", callback);

	var uvrun = require("uvrun");
	while (!res)
	uvrun.runOnce();
	return res;
}

x = read();
console.log(x);