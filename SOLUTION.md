# Political and Postal Communities of Switzerland
## Solution

This is my solution to the coding challenge. I took some decisions that I'd like to explain here to give a little bit of context.

### git history

The git history is relevant. The commits are organized meaningfully and show my incremental approach to solving the problem. Some steps are implementation, some steps are refactoring, some others are just reformatting or similar.

### Formatting
I initially tried automatic formatting within my editor, but I soon realised that the setup wasn't working properly. I ultimately added a package that creates a git hook and automatically formats code when committing. Ideally that should have been a first step.

### Refactoring
I refactored the CSV parsing code to make it more functional and make it more testable. While not strictly necessary, it allowed me to reduce code duplication.

### Testing
I didn't add any new tests on top of those existing to guide the solution. However the test coverage is probably not optimal and some more unit tests are probably warranted. I decided not to focus on that aspect for this excercise because I'm not too familiar with jUnit and it would have required a larger amount of time and I would rather have a working solution first, before refactoring the tests to be more comprehensive.

### Interfaces to classes
I decided to transform the given interfaces to classes. I thought about this and I felt uncomfortable with having the interface and a `CantonImpl` or `DefaultCanton` class to implement that interface because I do not see it very likely that there will ever be a different implementation for those interfaces. If my assumptions turn out to be wrong, it is an easy refactor.

### Builder pattern
I avoided the builder pattern because I felt it wasn't necessary. We always have all the parameters we need and the builder pattern doesn't add anything to what we have right now. This might change with the evolution of the software, but I always try to abide by the YAGNI pricinple.

### Internal data structure
I opted for a `Map` as an internal data structure for postal communities and political communities. It is not necessarily the best data structure, but it highly depends on the type of operations that are going to be more common in the future. The `Map` gives more of a "database-like" functionaly, where lookup by an identifier is very fast.

The size of the data, as well as the simple usages that give no indication as to which is the most common operation to be executed, are not enough to make a meaningful decision. Again, the actual implementation in `ApplicationModel` can very easily be refactored to a different data structure, if necessary.

### Memoization
I chose to prefer data transformations to having objects with arbitrarily long dependency chains. I am trading off a simpler representation at the expense of some computation.

While a more structured data could require less computation for some of the answers, it might make the creation of such objects more complicated, and it could lead to incosistent state in different objects.

A possible mitigation for the computations would be to memoize the results of some functions, so that they don't have to be repeated. However, the current data size and usage do not justify such an effort.

### Bugs
There is a bug in the original program. I added a commented out test to highlight this bug, which is a problem in the requirements: it is not possible to get a unique district name out of a ZIP code because the function is not reversible (a.k.a. multivalued function, or non-bijective function): there are some ZIP codes that belong to more than one district. An example of such a ZIP code is `8866` which is both in Canton Glarus and St. Gallen.