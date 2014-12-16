// core.js
function Pair(x :any, y :any) :Object{
	return {fst: x, snd: y};
}

var readline = require('readline'),
    rl = readline.createInterface({
        input: process.stdin,
        output: process.stdout
    });

rl.on('line', function() {
    rl.close();
})

function read(callback) {
    rl.question("Please enter input> ", function(answer) {
        callback(answer, rl);
    });
}

function print(s :string) :void {
	rl.write(s + '\n');
}

function terminate() :void {
	rl.pause();
	rl.close();
}

module.exports = {
	print: print, 
	read: read,
	terminate: terminate,
	iostream : rl
}