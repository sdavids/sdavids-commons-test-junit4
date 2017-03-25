/*
 * Copyright (c) 2017, Sebastian Davids
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.sdavids.commons.test.junit4;

import static java.util.Locale.ENGLISH;
import static java.util.Locale.GERMAN;
import static java.util.Locale.GERMANY;
import static java.util.Locale.US;
import static java.util.Locale.forLanguageTag;
import static java.util.Objects.requireNonNull;

import com.google.common.annotations.VisibleForTesting;
import java.util.Locale;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

/**
 * The {@code DefaultLocaleRule} rule allows one to run a test in a specific locale.
 *
 * <h3 id="usage">Usage</h3>
 *
 * <pre><code>
 * public class SimpleDefaultLocaleRuleTest {
 *
 *     &#64;Rule
 *     public DefaultLocaleRule locale = DefaultLocaleRule.tr();
 *
 *     &#64;Test
 *     public void runInTurkishLocale() {
 *         // Locale.getDefault()  &#8658; tr
 *         // "mail".toUpperCase() &#8658; MAİL
 *     }
 *
 *     &#64;Test
 *     public void runInFranceLocale() {
 *         locale.useLocale(Locale.FRANCE);
 *
 *         // Locale.getDefault()                   &#8658; fr-FR
 *         // System.out.printf("%,.2f", 12_345.67) &#8658; 12 345,67
 *     }
 * }
 * </code></pre>
 *
 * @since 1.0
 */
public final class DefaultLocaleRule extends TestWatcher {

  /**
   * Creates a new rule in the English locale ("en").
   *
   * @return the rule
   * @since 1.0
   */
  public static DefaultLocaleRule en() {
    return new DefaultLocaleRule(ENGLISH);
  }

  /**
   * Creates a new rule in the English (United States) locale ("en-US").
   *
   * @return the rule
   * @since 1.0
   */
  public static DefaultLocaleRule enUS() {
    return new DefaultLocaleRule(US);
  }

  /**
   * Creates a new rule in the German locale ("de")).
   *
   * @return the rule
   * @since 1.0
   */
  public static DefaultLocaleRule de() {
    return new DefaultLocaleRule(GERMAN);
  }

  /**
   * Creates a new rule in the German (Germany) locale ("de-DE")).
   *
   * @return the rule
   * @since 1.0
   */
  public static DefaultLocaleRule deDE() {
    return new DefaultLocaleRule(GERMANY);
  }

  /**
   * Creates a new rule in the Turkish locale ("tr")).
   *
   * @return the rule
   * @since 1.0
   */
  public static DefaultLocaleRule tr() {
    return new DefaultLocaleRule(forLanguageTag("tr"));
  }

  /**
   * Creates a new rule in the Turkish (Turkey) locale ("tr-TR")).
   *
   * @return the rule
   * @since 1.0
   */
  public static DefaultLocaleRule trTR() {
    return new DefaultLocaleRule(forLanguageTag("tr-TR"));
  }

  /**
   * Creates a new rule for the specified locale.
   *
   * @param locale the locale, not null
   * @return the rule
   * @since 1.0
   */
  public static DefaultLocaleRule forLocale(Locale locale) {
    return new DefaultLocaleRule(requireNonNull(locale, "locale"));
  }

  private final Locale locale;

  private Locale systemLocale;

  private DefaultLocaleRule(Locale locale) {
    this.locale = locale;
  }

  /**
   * Sets the test method's locale to the specified one.
   *
   * @param locale the locale, not null
   * @since 1.0
   */
  public void useLocale(Locale locale) {
    Locale.setDefault(requireNonNull(locale, "locale"));
  }

  /**
   * Changes the test method's locale to the system's locale.
   *
   * @since 1.0
   */
  public void useSystemLocale() {
    Locale.setDefault(systemLocale);
  }

  @VisibleForTesting
  Locale getLocale() {
    return locale;
  }

  @Override
  protected void starting(Description description) {
    systemLocale = Locale.getDefault();

    Locale.setDefault(locale);
  }

  @Override
  protected void finished(Description description) {
    Locale.setDefault(systemLocale);
  }
}
