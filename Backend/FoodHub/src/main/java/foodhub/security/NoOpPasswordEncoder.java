package foodhub.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * To my knowledge, this class is unused; please disregard it.
 * It has to do with security.
 * @author Jamie Anderson
 */
public final class NoOpPasswordEncoder implements PasswordEncoder {

	private static final PasswordEncoder INSTANCE = new NoOpPasswordEncoder();

	NoOpPasswordEncoder() {}

	@Override
	public String encode(CharSequence password) {
		return password.toString();
	}

	@Override
	public boolean matches(CharSequence password, String encoded) {
		return password.toString().equals(encoded);
	}

	public static PasswordEncoder getInstance() {
		return INSTANCE;
	}

}