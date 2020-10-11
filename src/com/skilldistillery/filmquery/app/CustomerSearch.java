package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class CustomerSearch {

	Scanner input = new Scanner(System.in);

	public boolean runCustomerFunctions(){
		boolean keepGoing = menuSwitch();
		return keepGoing;
	}

	private boolean menuSwitch() {
		boolean keepGoing = true;
		DatabaseAccessorObject run = new DatabaseAccessorObject();
		String userSearch = "";
		
		  while(keepGoing) {
			  searchMenu();
			  String userInput = input.nextLine().toLowerCase();
		  switch (userInput) {
		  case "1": case "film": case "film name": case "name":
			  userSearch = promptForSearchParameter(1);
			  List<Film> searchFilmTitle = run.findFilmByTitle(userSearch);
			printFilmsList(searchFilmTitle);
			  break;
		  case "2": case "rating": case "r":
			  userSearch = promptForSearchParameter(2);
			  List<Film> filmsByRating = run.findFilmsByRating(userSearch);
			  printFilmsList(filmsByRating);
			  break;
		  case "3": case "actor": case "a": 
			   userSearch = promptForSearchParameter(3);
			   List<Film> filmsByActorName;
			filmsByActorName = run.findFilmsByActorName(userSearch);
			printFilmsList(filmsByActorName);
			  break;
		  case "4": case "genre":
			   userSearch = promptForSearchParameter(4);
			   List<Film> filmsByGenre = run.findFilmsByCategory(userSearch);
			   printFilmsList(filmsByGenre);
			  break;
		  case "5": case "general": case "search": case "general search":
			   userSearch = promptForSearchParameter(5);
			  break;
		  case "6": case "quit": case "q":
			  keepGoing = false;
			  break;
		default: System.out.println("Invalid entry\n");
		  }
		  }
		  return keepGoing;
		
	}

	private void printFilmsList(List<Film> filmsByRating) {
		int count = 0;
		for (Film film : filmsByRating) {
			System.out.println(film);
			film.printCast(film.getActors());
			System.out.println("\n==================================================================================================\n");
			count++;
		}
		System.out.println("Your search returned " + count + " results");
	}

	private String promptForSearchParameter(int searchType) {
		String searchHeader = "";
				if (searchType == 1) {
					searchHeader = "FILM NAME";
					
				}else if(searchType == 2) {
					searchHeader = "RATING";
					
				}else if(searchType == 3) {
					searchHeader = "ACTOR/ACTRESS";
					
				}else if(searchType == 4) {
					searchHeader = "GENRE";
					
				}else if(searchType == 5) {
					searchHeader = "KEYWORD";
				}
		printSearchParameterPrompt(searchHeader);
		String userSearch = input.nextLine();
		return userSearch;
		
	}

	private void printSearchParameterPrompt(String searchHeader) {
	
		System.out.print("   _____                 _   \n" + 
				"  / ____|               | |  \n" + 
				" | |  __ _   _  ___  ___| |_ \n" + 
				" | | |_ | | | |/ _ \\/ __| __|\n" + 
				" | |__| | |_| |  __/\\__ \\ |_ \n" + 
				"  \\_____|\\__,_|\\___||___/\\__|\n" + 
				"                            ");
		System.out.println("\n\n\n<================================>");
		System.out.println("|                                |");
		System.out.println("|"+ Main.center("PLEASE ENTER YOUR", 32, (char)32) + "|");
		System.out.println("|                                |");
		System.out.println("|"+ Main.center(searchHeader, 32, (char)32) + "|");
		System.out.println("|                                |");
		System.out.println("|"+ Main.center("TO SEARCH", 32, (char)32) + "|");
		System.out.println("|                                |");
		System.out.println("<================================>");}
		
	

	private void searchMenu() {
System.out.print("   _____                 _   \n" + 
		"  / ____|               | |  \n" + 
		" | |  __ _   _  ___  ___| |_ \n" + 
		" | | |_ | | | |/ _ \\/ __| __|\n" + 
		" | |__| | |_| |  __/\\__ \\ |_ \n" + 
		"  \\_____|\\__,_|\\___||___/\\__|\n" + 
		"                            ");
		System.out.println("\n\n\n<================================>");
		System.out.println("|           SEARCH MENU          |");
		System.out.println("|                                |");
		System.out.println("|   1: BY FILM NAME              |");
		System.out.println("|   2: BY RATING                 |");
		System.out.println("|   3: BY ACTOR                  |");
		System.out.println("|   4: BY GENRE                  |");
		System.out.println("|   5: GENERAL SEARCH            |");
		System.out.println("|   6: QUIT                      |");
		System.out.println("|                                |");
		System.out.println("<================================>");}
}
class Main {
	  /**
	   * <p>The maximum size to which the padding constant(s) can expand.</p>
	   */
	  private static final int PAD_LIMIT = 8192;
	  
	  // Centering
	  //-----------------------------------------------------------------------
	  /**
	   * <p>Centers a String in a larger String of size <code>size</code>
	   * using the space character (' ').<p>
	   *
	   * <p>If the size is less than the String length, the String is returned.
	   * A <code>null</code> String returns <code>null</code>.
	   * A negative size is treated as zero.</p>
	   *
	   * <p>Equivalent to <code>center(str, size, " ")</code>.</p>
	   *
	   * <pre>
	   * StringUtils.center(null, *)   = null
	   * StringUtils.center("", 4)     = "    "
	   * StringUtils.center("ab", -1)  = "ab"
	   * StringUtils.center("ab", 4)   = " ab "
	   * StringUtils.center("abcd", 2) = "abcd"
	   * StringUtils.center("a", 4)    = " a  "
	   * </pre>
	   *
	   * @param str  the String to center, may be null
	   * @param size  the int size of new String, negative treated as zero
	   * @return centered String, <code>null</code> if null String input
	   */
	  public static String center(String str, int size) {
	      return center(str, size, ' ');
	  }

	  /**
	   * <p>Centers a String in a larger String of size <code>size</code>.
	   * Uses a supplied character as the value to pad the String with.</p>
	   *
	   * <p>If the size is less than the String length, the String is returned.
	   * A <code>null</code> String returns <code>null</code>.
	   * A negative size is treated as zero.</p>
	   *
	   * <pre>
	   * StringUtils.center(null, *, *)     = null
	   * StringUtils.center("", 4, ' ')     = "    "
	   * StringUtils.center("ab", -1, ' ')  = "ab"
	   * StringUtils.center("ab", 4, ' ')   = " ab"
	   * StringUtils.center("abcd", 2, ' ') = "abcd"
	   * StringUtils.center("a", 4, ' ')    = " a  "
	   * StringUtils.center("a", 4, 'y')    = "yayy"
	   * </pre>
	   *
	   * @param str  the String to center, may be null
	   * @param size  the int size of new String, negative treated as zero
	   * @param padChar  the character to pad the new String with
	   * @return centered String, <code>null</code> if null String input
	   * @since 2.0
	   */
	  public static String center(String str, int size, char padChar) {
	      if (str == null || size <= 0) {
	          return str;
	      }
	      int strLen = str.length();
	      int pads = size - strLen;
	      if (pads <= 0) {
	          return str;
	      }
	      str = leftPad(str, strLen + pads / 2, padChar);
	      str = rightPad(str, size, padChar);
	      return str;
	  }

	  /**
	   * <p>Centers a String in a larger String of size <code>size</code>.
	   * Uses a supplied String as the value to pad the String with.</p>
	   *
	   * <p>If the size is less than the String length, the String is returned.
	   * A <code>null</code> String returns <code>null</code>.
	   * A negative size is treated as zero.</p>
	   *
	   * <pre>
	   * StringUtils.center(null, *, *)     = null
	   * StringUtils.center("", 4, " ")     = "    "
	   * StringUtils.center("ab", -1, " ")  = "ab"
	   * StringUtils.center("ab", 4, " ")   = " ab"
	   * StringUtils.center("abcd", 2, " ") = "abcd"
	   * StringUtils.center("a", 4, " ")    = " a  "
	   * StringUtils.center("a", 4, "yz")   = "yayz"
	   * StringUtils.center("abc", 7, null) = "  abc  "
	   * StringUtils.center("abc", 7, "")   = "  abc  "
	   * </pre>
	   *
	   * @param str  the String to center, may be null
	   * @param size  the int size of new String, negative treated as zero
	   * @param padStr  the String to pad the new String with, must not be null or empty
	   * @return centered String, <code>null</code> if null String input
	   * @throws IllegalArgumentException if padStr is <code>null</code> or empty
	   */
	  public static String center(String str, int size, String padStr) {
	      if (str == null || size <= 0) {
	          return str;
	      }
	      if (isEmpty(padStr)) {
	          padStr = " ";
	      }
	      int strLen = str.length();
	      int pads = size - strLen;
	      if (pads <= 0) {
	          return str;
	      }
	      str = leftPad(str, strLen + pads / 2, padStr);
	      str = rightPad(str, size, padStr);
	      return str;
	  }


	  /**
	   * <p>Right pad a String with a specified character.</p>
	   *
	   * <p>The String is padded to the size of <code>size</code>.</p>
	   *
	   * <pre>
	   * StringUtils.rightPad(null, *, *)     = null
	   * StringUtils.rightPad("", 3, 'z')     = "zzz"
	   * StringUtils.rightPad("bat", 3, 'z')  = "bat"
	   * StringUtils.rightPad("bat", 5, 'z')  = "batzz"
	   * StringUtils.rightPad("bat", 1, 'z')  = "bat"
	   * StringUtils.rightPad("bat", -1, 'z') = "bat"
	   * </pre>
	   *
	   * @param str  the String to pad out, may be null
	   * @param size  the size to pad to
	   * @param padChar  the character to pad with
	   * @return right padded String or original String if no padding is necessary,
	   *  <code>null</code> if null String input
	   * @since 2.0
	   */
	  public static String rightPad(String str, int size, char padChar) {
	      if (str == null) {
	          return null;
	      }
	      int pads = size - str.length();
	      if (pads <= 0) {
	          return str; // returns original String when possible
	      }
	      if (pads > PAD_LIMIT) {
	          return rightPad(str, size, String.valueOf(padChar));
	      }
	      return str.concat(padding(pads, padChar));
	  }

	  /**
	   * <p>Right pad a String with a specified String.</p>
	   *
	   * <p>The String is padded to the size of <code>size</code>.</p>
	   *
	   * <pre>
	   * StringUtils.rightPad(null, *, *)      = null
	   * StringUtils.rightPad("", 3, "z")      = "zzz"
	   * StringUtils.rightPad("bat", 3, "yz")  = "bat"
	   * StringUtils.rightPad("bat", 5, "yz")  = "batyz"
	   * StringUtils.rightPad("bat", 8, "yz")  = "batyzyzy"
	   * StringUtils.rightPad("bat", 1, "yz")  = "bat"
	   * StringUtils.rightPad("bat", -1, "yz") = "bat"
	   * StringUtils.rightPad("bat", 5, null)  = "bat  "
	   * StringUtils.rightPad("bat", 5, "")    = "bat  "
	   * </pre>
	   *
	   * @param str  the String to pad out, may be null
	   * @param size  the size to pad to
	   * @param padStr  the String to pad with, null or empty treated as single space
	   * @return right padded String or original String if no padding is necessary,
	   *  <code>null</code> if null String input
	   */
	  public static String rightPad(String str, int size, String padStr) {
	      if (str == null) {
	          return null;
	      }
	      if (isEmpty(padStr)) {
	          padStr = " ";
	      }
	      int padLen = padStr.length();
	      int strLen = str.length();
	      int pads = size - strLen;
	      if (pads <= 0) {
	          return str; // returns original String when possible
	      }
	      if (padLen == 1 && pads <= PAD_LIMIT) {
	          return rightPad(str, size, padStr.charAt(0));
	      }

	      if (pads == padLen) {
	          return str.concat(padStr);
	      } else if (pads < padLen) {
	          return str.concat(padStr.substring(0, pads));
	      } else {
	          char[] padding = new char[pads];
	          char[] padChars = padStr.toCharArray();
	          for (int i = 0; i < pads; i++) {
	              padding[i] = padChars[i % padLen];
	          }
	          return str.concat(new String(padding));
	      }
	  }

	  /**
	   * <p>Left pad a String with a specified character.</p>
	   *
	   * <p>Pad to a size of <code>size</code>.</p>
	   *
	   * <pre>
	   * StringUtils.leftPad(null, *, *)     = null
	   * StringUtils.leftPad("", 3, 'z')     = "zzz"
	   * StringUtils.leftPad("bat", 3, 'z')  = "bat"
	   * StringUtils.leftPad("bat", 5, 'z')  = "zzbat"
	   * StringUtils.leftPad("bat", 1, 'z')  = "bat"
	   * StringUtils.leftPad("bat", -1, 'z') = "bat"
	   * </pre>
	   *
	   * @param str  the String to pad out, may be null
	   * @param size  the size to pad to
	   * @param padChar  the character to pad with
	   * @return left padded String or original String if no padding is necessary,
	   *  <code>null</code> if null String input
	   * @since 2.0
	   */
	  public static String leftPad(String str, int size, char padChar) {
	      if (str == null) {
	          return null;
	      }
	      int pads = size - str.length();
	      if (pads <= 0) {
	          return str; // returns original String when possible
	      }
	      if (pads > PAD_LIMIT) {
	          return leftPad(str, size, String.valueOf(padChar));
	      }
	      return padding(pads, padChar).concat(str);
	  }

	  /**
	   * <p>Left pad a String with a specified String.</p>
	   *
	   * <p>Pad to a size of <code>size</code>.</p>
	   *
	   * <pre>
	   * StringUtils.leftPad(null, *, *)      = null
	   * StringUtils.leftPad("", 3, "z")      = "zzz"
	   * StringUtils.leftPad("bat", 3, "yz")  = "bat"
	   * StringUtils.leftPad("bat", 5, "yz")  = "yzbat"
	   * StringUtils.leftPad("bat", 8, "yz")  = "yzyzybat"
	   * StringUtils.leftPad("bat", 1, "yz")  = "bat"
	   * StringUtils.leftPad("bat", -1, "yz") = "bat"
	   * StringUtils.leftPad("bat", 5, null)  = "  bat"
	   * StringUtils.leftPad("bat", 5, "")    = "  bat"
	   * </pre>
	   *
	   * @param str  the String to pad out, may be null
	   * @param size  the size to pad to
	   * @param padStr  the String to pad with, null or empty treated as single space
	   * @return left padded String or original String if no padding is necessary,
	   *  <code>null</code> if null String input
	   */
	  public static String leftPad(String str, int size, String padStr) {
	      if (str == null) {
	          return null;
	      }
	      if (isEmpty(padStr)) {
	          padStr = " ";
	      }
	      int padLen = padStr.length();
	      int strLen = str.length();
	      int pads = size - strLen;
	      if (pads <= 0) {
	          return str; // returns original String when possible
	      }
	      if (padLen == 1 && pads <= PAD_LIMIT) {
	          return leftPad(str, size, padStr.charAt(0));
	      }

	      if (pads == padLen) {
	          return padStr.concat(str);
	      } else if (pads < padLen) {
	          return padStr.substring(0, pads).concat(str);
	      } else {
	          char[] padding = new char[pads];
	          char[] padChars = padStr.toCharArray();
	          for (int i = 0; i < pads; i++) {
	              padding[i] = padChars[i % padLen];
	          }
	          return new String(padding).concat(str);
	      }
	  }
	  /**
	   * <p>Returns padding using the specified delimiter repeated
	   * to a given length.</p>
	   *
	   * <pre>
	   * StringUtils.padding(0, 'e')  = ""
	   * StringUtils.padding(3, 'e')  = "eee"
	   * StringUtils.padding(-2, 'e') = IndexOutOfBoundsException
	   * </pre>
	   *
	   * <p>Note: this method doesn't not support padding with
	   * <a href="http://www.unicode.org/glossary/#supplementary_character">Unicode Supplementary Characters</a>
	   * as they require a pair of <code>char</code>s to be represented.
	   * If you are needing to support full I18N of your applications
	   * consider using {@link #repeat(String, int)} instead. 
	   * </p>
	   *
	   * @param repeat  number of times to repeat delim
	   * @param padChar  character to repeat
	   * @return String with repeated character
	   * @throws IndexOutOfBoundsException if <code>repeat &lt; 0</code>
	   * @see #repeat(String, int)
	   */
	  private static String padding(int repeat, char padChar) throws IndexOutOfBoundsException {
	      if (repeat < 0) {
	          throw new IndexOutOfBoundsException("Cannot pad a negative amount: " + repeat);
	      }
	      final char[] buf = new char[repeat];
	      for (int i = 0; i < buf.length; i++) {
	          buf[i] = padChar;
	      }
	      return new String(buf);
	  }
	  // Empty checks
	  //-----------------------------------------------------------------------
	  /**
	   * <p>Checks if a String is empty ("") or null.</p>
	   *
	   * <pre>
	   * StringUtils.isEmpty(null)      = true
	   * StringUtils.isEmpty("")        = true
	   * StringUtils.isEmpty(" ")       = false
	   * StringUtils.isEmpty("bob")     = false
	   * StringUtils.isEmpty("  bob  ") = false
	   * </pre>
	   *
	   * <p>NOTE: This method changed in Lang version 2.0.
	   * It no longer trims the String.
	   * That functionality is available in isBlank().</p>
	   *
	   * @param str  the String to check, may be null
	   * @return <code>true</code> if the String is empty or null
	   */
	  public static boolean isEmpty(String str) {
	      return str == null || str.length() == 0;
	  }
	}