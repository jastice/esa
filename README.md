# Explicit Semantic Analysis in Java

This is a rudimentary Java implementation of [Explicit Semantic Analysis](http://en.wikipedia.org/wiki/Explicit_semantic_analysis) based on the paper ["Concept-Based Information Retrieval using Explicit Semantic Analysis" (Ofer Egozi, Shaul Markovitch and Evgeniy Gabrilovich (2011))](http://www.cs.technion.ac.il/~gabr/publications/papers/Egozi2011CBI.pdf). I implemented it for a class project, so it is in no way production-usable or fully documented, but the code is pretty clear, I think, so it should give you a good starting point.

## Building

You can build this project with Eclipse or IntelliJ. The only dependency is [JWPL](https://code.google.com/p/jwpl/) and is included in the project.

## Running

You need to build an SQL database from a Wikipedia dump with JWPL. I forgot how to do this, but it wasn't too hard. Set up the connection parameters in `justinkaeser.esa.app.Properties.java`.

`app.TopConcepts` is a main method that takes a file and evaluates it against an index of a number of wikipedia documents. The index is built every time the program is started, which is kinda ineffecient, so there's a hack in `justinkaeser.esa.app.IOUtil` to limit the size when experimenting (`MAX_INDEX_SIZE`).

## Problems?

If you have trouble getting this to run, simply file an issue.

## License
Licensed under [LGPL](http://www.gnu.org/licenses/lgpl.html), just like JWPL. Or whatever other license is compatible.