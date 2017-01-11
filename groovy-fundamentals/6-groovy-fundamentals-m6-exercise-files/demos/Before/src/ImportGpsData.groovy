def file = new File('../data/fells_loop.gpx')

def slurper = new XmlSlurper()
def gpx = slurper.parse(file)
println gpx.name
println ''
println gpx.desc
println ''
println gpx.@version
println gpx.@creator

gpx.rte.rtept.each {
	println it.@lat
	println it.@lon

	def parser = new DateParser()
	println parser.parse(it.time.toString())

}
