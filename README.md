# Xml Formatter

Just a pet project I wanna try without looking at any formatting plugin code.
This is a CLI tool that I would like to use for formatting badly formatted XMLs I encounter especially at work.

_I swear I'm gonna rewrite this in Rust_

Rules used on this formatter is based from [W3Schools XML Syntax Rules](https://www.w3schools.com/xml/xml_syntax.asp)

Here's how I roughly think it should parse and format

```
1 | <?xml version="1.0" encoding="UTF-8"?>
2 | <note>
3 |   <to>Tove</to>
4 |   <from>Jani</from>
5 |   <heading>Reminder</heading>
6 |   <body>Don't forget me this weekend!</body>
7 | </note>
```

- Line 1 is the Xml prolog. Just scan buffer from `<` to `>` take that as as a special Node (or Tree? I guess)
- Construct Tree objects based on tags
  - Tree Rules:
    1. Open tag is root of tree
    2. scan buffer until in finds next `<` then check if next char is `/` - this means it's an End tag.
    3. If End tag, save Tree object in an List (need to keep order)
    4. If next `<` is not an End tag, check whether Tree is complete (End tag reached), otherwise create a Node.
- When to construct a Node:
  - Node Rules:
    1. Create new Node object only when

1. Collapse: remove all sorts of unnecessary whitespace characters
2. Decompose: define sections and kind of dispatch to a queue
3. Rebuild: Rebuild file into proper format

### Running the CLI app:

To run the application application when checkedout, use _`gradle run --args="<path to XML to be parsed>"`_
