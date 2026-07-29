import unittest

from rot13.cipher import is_valid, rot13


class Rot13Tests(unittest.TestCase):
    def test_known_vector(self) -> None:
        self.assertEqual(rot13("HELLO"), "URYYB")

    def test_is_its_own_inverse(self) -> None:
        message = "The quick brown fox"
        self.assertEqual(rot13(rot13(message)), message)

    def test_spaces_are_preserved(self) -> None:
        self.assertEqual(rot13("a b c"), "n o p")

    def test_lowercase_wraps(self) -> None:
        self.assertEqual(rot13("abc"), "nop")

    def test_rejects_non_letters(self) -> None:
        self.assertFalse(is_valid("hello123"))
        with self.assertRaises(ValueError):
            rot13("hello!")


if __name__ == "__main__":
    unittest.main()
