/* @flow */

function substr(s :string, i :number, j :number) :string {
	return s.substring(i, j);
}

function toLowerCase(s :string) :string {
	return s.toLowerCase();
}

function toUpperCase(s :string) :string {
	return s.toUpperCase();
}

function concat(s1 :string, s2 :string) :string {
	return s1.concat(s2);
}

function replace(s :string, substring :string, replacement :string) :string {
	return s.replace(substring, replacement);
}