require(["jquery"], function($) {
	var $stdin = $('#stdin'),
	$stdout = $('#stdout');

	$('#submit').on('click', function() {
		var s = read();
		print(s);
	})

	function read() {
		return $stdin.val();
	}

	function print(s) {
		$stdout.text(s);
	}
});