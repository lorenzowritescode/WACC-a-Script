/* @flow */
function square(x :number) :number {
	return x * x;
}


function log(x :number) :number {
	return Math.log10(x);
}


function arraySum(xs :Array<number>) :number {

	function foldSum(previousValue :number, currentValue :number, index :number, array :Array<number>) :number {
		return previousValue + currentValue;
	}

	return xs.reduce(foldSum, 0);
}


function arrayAvg(xs :Array<number>) :number {
	var sum :number = arraySum(xs);
	return sum / xs.length;
}