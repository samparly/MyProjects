package com.samy.leetcode.algorithm.easy;

import java.util.*;

/**
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 * Created by samy on 10/27/16.
 */
public class FindAllAnagramsinaString {

  public List<Integer> findAnagrams(String s, String p) {
    List<Integer> list = new ArrayList<>(s.length());
    int s_length = s.length(), p_length = p.length();
    if (p_length > s_length)
      return list;
    int[] a1 = new int[26];
    int[] a2 = new int[26];
    Set<Integer> pos = new HashSet<>();
    for (int i=0; i<p_length; ++i)
      a1[p.charAt(i) - 'a']++;
    int i;
    for (i=0; i<p_length; ++i) {
      a2[s.charAt(i) - 'a']++;
    }
    if (isAnagram(a1, a2))
      pos.add(0);
    for (; i<s_length; ++i) {
      char c_pre = s.charAt(i-p_length);
      char c_cur = s.charAt(i);
      if ( c_pre == c_cur ) {
        if (pos.contains(i-p_length))
          pos.add(i-p_length +1);
      } else {
        a2[c_pre - 'a']--;
        a2[c_cur - 'a']++;
        if (isAnagram(a1, a2))
          pos.add(i-p_length + 1);
      }
    }

    Iterator<Integer> it = pos.iterator();
    while(it.hasNext())
      list.add(it.next());

    return list;
  }

  private boolean isAnagram(int[] a1, int[] a2) {
    for (int i=0, l=a1.length; i<l; ++i)
      if (a1[i] != a2[i])
        return false;
    return true;
  }

  public List<Integer> findAnagrams2(String s, String p) {
    List<Integer> list = new ArrayList<>();
    if(p.length() > s.length())
      return list;
    int[] letter = new int[26];
    for(char c: p.toCharArray())
      letter[c-'a']++;
    int[] spec = new int[26];
    int count = p.length(), i=0;
    // slide window
    while(i < s.length()) {
      if(i >= p.length()) {
        char c1 = s.charAt(i-p.length());
        if(letter[c1-'a']>0 && --spec[c1-'a']<letter[c1-'a']) {
          ++count;
        }
      }
      char c2 = s.charAt(i);
      if(letter[c2-'a']>0 && ++spec[c2-'a']<=letter[c2-'a']) {
        --count;
      }
      if(count == 0)
        list.add(i - p.length() + 1);
      ++i;
    }
    return list;
  }

  public static void main(String[] args) {
/*    String s = "cbaebabacd";
    String p = "abc";*/
    String s = "ababfefeagewtetwafewfwegwefwe";
    String p = "we";
    FindAllAnagramsinaString fa = new FindAllAnagramsinaString();
    fa.findAnagrams(s, p);

  }

}
