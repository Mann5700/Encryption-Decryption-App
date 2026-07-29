"""A small Tkinter GUI for the ROT-13 cipher.

Mirrors the original desktop layout: an *Encrypt* field, a *Decrypt* field, a
read-only *Result* box, and Convert / Clear buttons. Whichever of the two input
fields is filled in becomes the source text — because ROT-13 is its own inverse,
the same button both scrambles and unscrambles.
"""

from __future__ import annotations

import tkinter as tk
from tkinter import messagebox

from .cipher import rot13


class CipherApp(tk.Tk):
    """The main application window."""

    def __init__(self) -> None:
        super().__init__()
        self.title("Encryption / Decryption App — ROT-13")
        self.geometry("470x230")
        self.resizable(False, False)

        self.encrypt_var = tk.StringVar()
        self.decrypt_var = tk.StringVar()
        self.result_var = tk.StringVar()

        self._build_form()

    def _build_form(self) -> None:
        pad = {"padx": 8, "pady": 6}

        tk.Label(self, text="Secret Letters", font=("Segoe UI", 12, "bold")).grid(
            row=0, column=0, columnspan=2, pady=(14, 4)
        )

        tk.Label(self, text="Encrypt").grid(row=1, column=0, sticky="e", **pad)
        tk.Entry(self, textvariable=self.encrypt_var, width=34).grid(row=1, column=1, **pad)

        tk.Label(self, text="Decrypt").grid(row=2, column=0, sticky="e", **pad)
        tk.Entry(self, textvariable=self.decrypt_var, width=34).grid(row=2, column=1, **pad)

        tk.Label(self, text="Result").grid(row=3, column=0, sticky="e", **pad)
        tk.Entry(
            self, textvariable=self.result_var, width=34, state="readonly"
        ).grid(row=3, column=1, **pad)

        buttons = tk.Frame(self)
        buttons.grid(row=4, column=0, columnspan=2, pady=14)
        tk.Button(buttons, text="Convert", width=12, command=self.convert).pack(
            side="left", padx=6
        )
        tk.Button(buttons, text="Clear", width=12, command=self.clear).pack(
            side="left", padx=6
        )

    def convert(self) -> None:
        """Transform whichever input field is filled and show the result."""
        source = self.decrypt_var.get() or self.encrypt_var.get()
        try:
            self.result_var.set(rot13(source))
        except ValueError as error:
            messagebox.showerror("Invalid input", str(error))

    def clear(self) -> None:
        self.encrypt_var.set("")
        self.decrypt_var.set("")
        self.result_var.set("")


def main() -> None:
    CipherApp().mainloop()


if __name__ == "__main__":
    main()
