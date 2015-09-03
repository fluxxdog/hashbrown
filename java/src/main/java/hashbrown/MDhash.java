package hashbrown;

import java.util.function.Function;

import javax.xml.bind.DatatypeConverter;

import gnu.crypto.hash.HashFactory;
import gnu.crypto.hash.IMessageDigest;

enum MDhash implements Function<String, String>{
		HAVAL, MD5, RIPEMD128, RIPEMD160, SHA256("SHA-256"), SHA384("SHA-384"), SHA512("SHA-512"), TIGER, WHIRLPOOL;
		MDhash(){
			this.setMessageDigest(this.name());
		}

		MDhash(final String s){
			this.setMessageDigest(s);
		}

		private IMessageDigest messageDigest;

		@Override
		public String apply(final String incoming){
			this.messageDigest.reset();
			final byte[] bytes= incoming.getBytes();
			this.messageDigest.update(bytes, 0, bytes.length);
			return DatatypeConverter.printHexBinary(this.messageDigest.digest());
		}

		private void setMessageDigest(final String s){
			this.messageDigest= HashFactory.getInstance(s.toString());
		}
}
