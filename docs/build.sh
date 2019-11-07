#!/bin/bash

# build.sh is a script to generate the final PDF document of requirements

# Dependencies: mermaid-filter, pandoc, pdflatex

function generate() {


  echo "---"
  echo "title: Requirements PDF"
  echo "geometry: margin=3cm"
  echo "author: Shawarma"
  echo "---"
  echo ""
  echo ""

  echo "\includepdf{project-generic-diagrams/use-case-diagram.pdf}"


  cat use-cases/all-use-cases-merged.md
  echo ""
  echo "$\pagebreak$"
  echo ""

  echo "\includepdf{project-generic-diagrams/domain-model-diagram.pdf}"

  echo "# System Sequence Diagrams"
  for file in system-sequence-diagrams/*.md; do
    echo ""
    echo ""
    cat $file
    echo ""
    echo ""
    echo "---"
    echo ""
    echo ""
  done

  echo ""
  echo "$\pagebreak$"
  echo ""

  cat operation-contracts/all-operation-contracts-merged.md
  echo ""
  echo "$\pagebreak$"
  echo ""

  cat supplementary-specifications.md
  echo ""
  echo "$\pagebreak$"
  echo ""
  cat Glossary.md
  echo ""
  echo "$\pagebreak$"
  echo ""
  cat vision/vision.md
  echo ""
  echo "$\pagebreak$"
  echo ""
}


generate > /tmp/generated.md
pandoc -V header-includes="\usepackage{pdfpages}"  -F mermaid-filter --toc -o kaboom.pdf /tmp/generated.md
