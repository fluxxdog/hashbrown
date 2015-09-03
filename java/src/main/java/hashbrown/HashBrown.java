package hashbrown;

import java.security.MessageDigest;

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
public class HashBrown{
		private HashBrown(){
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
		public static String served(final String username, final String passphrase){
			String salt= ""; // If desired
			String cooking= MDhash.MD5.apply(username);
			final MDhash[] hasharray= HashBrown.getHashArray(username.toLowerCase().charAt(0));
			final String[] words= passphrase.split(" ");
			for(int i= 0; i < words.length; i++){
				cooking+= hasharray[i + 1].apply(words[i]);
			}
			final String readyToServe= hasharray[words.length].apply(salt + cooking);
			return readyToServe;
		}

		private static MDhash[] getHashArray(final char charAt){
			MDhash[] mda;
			/*
			 * This is written with the assumption of a 4-word passphrase system.
			 * Each array should have 1 hash for each word of the passphrase, and
			 * 1 for the final hash. Essentially, provide for the number of words
			 * in the passphrase plus one.
			 */
			switch(charAt){
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
			case 'a':
			case 'b':
			case 'c':
			case 'd':
			case 'e':
			case 'f':
				mda= new MDhash[]{MDhash.MD5, MDhash.RIPEMD128, MDhash.WHIRLPOOL, MDhash.SHA256, MDhash.TIGER};
			default:
				mda= new MDHash[0];
			}
			return mda;
		}
}
