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
import static java.util.Locale.FRANCE;
import static java.util.Locale.GERMAN;
import static java.util.Locale.GERMANY;
import static java.util.Locale.US;
import static java.util.Locale.forLanguageTag;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;
import org.junit.Rule;
import org.junit.Test;

public final class DefaultLocaleRuleTest {

  private static final Locale systemLocale = Locale.getDefault();

  @Rule public DefaultLocaleRule locale = DefaultLocaleRule.tr();

  @Test
  public void defaultSet() {
    assertThat(Locale.getDefault()).isEqualTo(forLanguageTag("tr"));
  }

  @Test
  public void useLocale() {
    Locale france = FRANCE;

    assertThat(Locale.getDefault()).isEqualTo(forLanguageTag("tr"));

    locale.useLocale(france);

    assertThat(Locale.getDefault()).isEqualTo(france);
  }

  @Test
  public void useSystemLocale() {
    assertThat(Locale.getDefault()).isEqualTo(forLanguageTag("tr"));

    locale.useSystemLocale();

    assertThat(Locale.getDefault()).isEqualTo(systemLocale);
  }

  @Test
  public void en() {
    assertThat(DefaultLocaleRule.en().getLocale()).isEqualTo(ENGLISH);
  }

  @Test
  public void enUS() {
    assertThat(DefaultLocaleRule.enUS().getLocale()).isEqualTo(US);
  }

  @Test
  public void de() {
    assertThat(DefaultLocaleRule.de().getLocale()).isEqualTo(GERMAN);
  }

  @Test
  public void deDE() {
    assertThat(DefaultLocaleRule.deDE().getLocale()).isEqualTo(GERMANY);
  }

  @Test
  public void tr() {
    assertThat(DefaultLocaleRule.tr().getLocale()).isEqualTo(forLanguageTag("tr"));
  }

  @Test
  public void trTR() {
    assertThat(DefaultLocaleRule.trTR().getLocale()).isEqualTo(forLanguageTag("tr-TR"));
  }

  @Test
  public void forLocale() {
    Locale france = FRANCE;

    assertThat(DefaultLocaleRule.forLocale(france).getLocale()).isEqualTo(france);
  }
}
