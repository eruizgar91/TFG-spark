object UpCase {
 def main(args: Array[String]) {
 	class Upper {
		def upper(strings: String*): Seq[String] = {
			strings.map((s:String) => s.toUpperCase())
		}
	}
	val up = new Upper
	Console.println(up.upper("A", "First", "Scala", "Program"))
	 }
}
