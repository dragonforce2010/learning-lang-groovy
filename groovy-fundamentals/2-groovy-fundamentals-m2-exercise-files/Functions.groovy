def numbers = 0..9
for (num in numbers) {
	if (isEven(num)) {
		println num
	}
}

def isEven(num) {
	num%2 == 0
}
