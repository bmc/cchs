---
title: cchs
layout: default
---

_cchs_ is a quick-and-dirty program that converts a text file from one
character encoding to another. (I finally grew tired of writing it over
and over again, and I decided to save it.)

# Installation

To install, use Nathan Hamblen's [conscript][], like so:

    $ cs bmc/cchs

**conscript** will install a _cchs_ binary in `~/bin` for you.

# Usage

Run _cchs_ like this:

    $ cchs input-path input-encoding output-path output-encoding

`input-encoding` and `output-encoding` can be any character encoding
accepted by the Java runtime.

# License and Copyright

_cchs_ is copyright &copy; 2013 Brian M. Clapper and is released under a
[BSD license](https://github.com/bmc/qr/blob/master/LICENSE.md).

[conscript]: https://github.com/n8han/conscript
