function find(selector) {
	if (typeof selector === "string") {
		if (/^#.+$/.test(selector)) {// id
			return document.getElementById(selector.substr(1));
		} else if (/^\..+$/.test(selector)) {// class
			var tmp = document.getElementsByClassName(selector.substr(1));
			return tmp.length == 1 ? tmp[0] : tmp;
		} else {// tag
			var tmp = document.getElementsByTagName(selector);
			return tmp.length == 1 ? tmp[0] : tmp;
		}
	}
}