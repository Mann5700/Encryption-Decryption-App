"""Command-line interface for the ROT-13 cipher.

Examples:
    python -m rot13.cli "Hello World"      # -> Uryyb Jbeyq
    echo "Uryyb Jbeyq" | python -m rot13.cli
"""

from __future__ import annotations

import argparse
import sys

from .cipher import rot13


def build_parser() -> argparse.ArgumentParser:
    parser = argparse.ArgumentParser(
        prog="rot13",
        description="Encrypt or decrypt text with the ROT-13 cipher.",
        epilog="ROT-13 is its own inverse, so the same command both scrambles and unscrambles.",
    )
    parser.add_argument(
        "text",
        nargs="*",
        help="Text to transform. If omitted, text is read from standard input.",
    )
    return parser


def main(argv: list[str] | None = None) -> int:
    args = build_parser().parse_args(argv)

    source = " ".join(args.text) if args.text else sys.stdin.read().strip()

    try:
        print(rot13(source))
    except ValueError as error:
        print(f"error: {error}", file=sys.stderr)
        return 1
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
