var _ = require('underscore'),
	ignores = ['#input#', '#output#', '#addrs#', '#runtime_error#', '#empty#', '(nil)'];

function isContaminated(expOut) {

	var contaminated = _.reduce(expOut, function(memo, val) {
		return memo || containsIgnores(val);
	}, false);

	return contaminated;
}

function containsIgnores(s) {
	return _.reduce(ignores, function (memo, val) {
		return memo || (s.indexOf(val) >= 0);
	}, false)
};

function arraysEqual(expOut, actualOut) {
	if (expOut === actualOut) 
		return codes.success;

	if (isContaminated(expOut))
		return codes.dontknow;

	if (expOut.length != actualOut.length)
		return codes.wrongOutput;

	for (var i = 0; i < expOut.length; ++i) {
		if (expOut[i] != actualOut[i])
			return codes.wrongOutput;
	}
	return codes.success;
}

function trim(expOut) {
	return _.filter(expOut, function(val) {
		return val != null && val != undefined && val != '';
	});
}

var codes = {
	success: 0,
	syntax: 1,
	runtime: 2,
	wrongOutput: 3,
	dontknow: 4
};

module.exports = {
	isContaminated: isContaminated,
	arraysEqual: arraysEqual,
	codes: codes,
	trim: trim
}