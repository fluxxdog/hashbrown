package main;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.function.Supplier;

import javax.xml.bind.DatatypeConverter;

///@formatter:off
/*
     hashbrown technique
    Copyright (C) 2015 Michael W. Fender

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along
    with this program; if not, write to the Free Software Foundation, Inc.,
    51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.

 */
//@formatter:on
/**
 * The Hashbrown technique is a method of constructing a one-way encryption.
 * Using a multi-word passphrase, it can hash a user name and each word of the
 * passphrase, hash them into a hexadecimal string, concatenate them, and hash
 * it one final time. The passphrase system is much more resistant to brute
 * force attacks, the hashes are more resistant to reverse engineering, and the
 * passphrase system is designed to be easier to remember (thus reducing the
 * need to write down a password somewhere).<br/>
 * <br/>
 *
 * This class provides one public, static method:
 * {@link #served(String, String)}<br/>
 * <br/>
 * To be properly used, this class must be customized. The basics are
 * implemented, but to be fully usable, combinations of hashing techniques must
 * be designed and added. It is advised that other methods are added as well as
 * Java is only guaranteed to use MD5, SHA-1, and SHA-256.
 *
 * @see MessageDigest
 */
public class HashBrown {
	/*
	 * Using jdk 1.8.0_51, 7 MessageDigests are provided. New ones should be
	 * added here with a way to reference them and create them.
	 */
	private enum MDhash implements Supplier<MessageDigest> {
		MD2, MD5, SHA, SHA224("SHA-224"), SHA256("SHA-256"), SHA384("SHA-384"), SHA512("SHA-512");
		MDhash() {
			this.setMessageDigest(this.name());
		}

		MDhash(final String s) {
			this.setMessageDigest(s);
		}

		private MessageDigest messageDigest;

		@Override
		public MessageDigest get() {
			this.messageDigest.reset();
			return this.messageDigest;
		}

		private void setMessageDigest(final String s) {
			try {
				this.messageDigest = MessageDigest.getInstance(s.toString());
			} catch (final NoSuchAlgorithmException e) {
				System.out.println("This implementation of Java does not use " + s);
				e.printStackTrace();
			}
		}
	}

	private HashBrown() {
	}

	/**
	 * Takes in a username and a passphrase and constructs a hash code string
	 * useful for one-way encryption. Properties of the hash code are
	 * determined by the implementor and hard-coded into the {@link HashBrown}
	 * class.
	 *
	 * @param username
	 * @param passphrase
	 * @return A hexadecimal string resulting from hashes of the username and
	 *         passphrase.
	 */
	public static String served(final String username, final String passphrase) {
		final String uName = username.toLowerCase();
		final MessageDigest[] hasharray = HashBrown.getHashArray(uName.charAt(0));
		String cooking = HashBrown.hash(hasharray[0], uName);
		final String[] words = passphrase.split(" ");
		for (int i = 0; i < words.length; i++) {
			cooking += HashBrown.hash(hasharray[i + 1], words[i]);
		}
		final String readyToServe = HashBrown.hash(hasharray[words.length + 1], cooking);
		return readyToServe;
	}

	private static MessageDigest[] constructHashArray(final String listing) {
		final String[] hashes = listing.split(",");
		final MessageDigest[] construction = new MessageDigest[hashes.length];
		for (int i = 0; i < hashes.length; i++) {
			construction[i] = MDhash.valueOf(hashes[i]).get();
		}
		return construction;
	}

	private static MessageDigest[] getHashArray(final char charAt) {
		MessageDigest[] mda;
		/*
		 * This is written with the assumption of a 4-word passphrase system.
		 * Each array should have 1 hash for the username, 1 for each word of
		 * the passphrase, and 1 for the final hash. Essentially, provide for
		 * the number of words in the passphrase plus two.
		 */
		switch (charAt) {
		default:
			mda = HashBrown.constructHashArray("MD2,MD5,SHA,SHA224,SHA256,SHA384");
		}
		return mda;
	}

	private static String hash(final MessageDigest messageDigest, final String input) {
		final byte[] prepared = input.getBytes();
		final byte[] processed = messageDigest.digest(prepared);
		final String presented = DatatypeConverter.printHexBinary(processed);
		return presented;
	}
}
