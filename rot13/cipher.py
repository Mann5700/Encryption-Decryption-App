"""Core ROT-13 substitution cipher.

ROT-13 ("rotate by 13 places") shifts every ASCII letter 13 positions through the
26-letter alphabet. Because ``26 / 2 == 13``, applying the transform twice returns
the original text, so the *same* function both encrypts and decrypts.
"""

from __future__ import annotations

import string

# Pre-computed translation table: A<->N, B<->O, ... for both upper and lower case.
_ROT13_TABLE = str.maketrans(
    string.ascii_uppercase + string.ascii_lowercase,
    (
        string.ascii_uppercase[13:]
        + string.ascii_uppercase[:13]
        + string.ascii_lowercase[13:]
        + string.ascii_lowercase[:13]
    ),
)

# Only letters and spaces are accepted, mirroring the original app's validation.
_ALLOWED = set(string.ascii_letters + " ")


def is_valid(text: str) -> bool:
    """Return ``True`` if *text* contains only letters and spaces."""
    return all(char in _ALLOWED for char in text)


def rot13(text: str) -> str:
    """Encrypt or decrypt *text* with ROT-13.

    Args:
        text: The message to transform. Letters and spaces only.

    Returns:
        The ROT-13 transformed text.

    Raises:
        ValueError: If *text* contains anything other than letters and spaces.
    """
    if not is_valid(text):
        raise ValueError("Enter alphabets and spaces only!")
    return text.translate(_ROT13_TABLE)
