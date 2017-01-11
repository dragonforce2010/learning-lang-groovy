def file = new File('../data/fells_loop.gpx')

def slurper = new XmlSlurper()
def gpx = slurper.parse(file)

gpx.with {
	println name
	println ''

	println desc
	println ''	

	println attributes()['version']
	println attributes()['creator']
}

gpx.rte.rtept.each {
	println it.@lat
	println it.@lon

	def parser = new DateParser()
	println parser.parse(it.time.toString())
}
