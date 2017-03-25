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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.TimeZone;
import org.junit.Rule;
import org.junit.Test;

public final class DefaultTimeZoneRuleTest {

  private static final TimeZone systemTimeZone = TimeZone.getDefault();

  @Rule public DefaultTimeZoneRule timeZone = DefaultTimeZoneRule.utc();

  @Test
  public void defaultSet() {
    assertThat(TimeZone.getDefault()).isEqualTo(TimeZone.getTimeZone("UTC"));
  }

  @Test
  public void useLocale() {
    TimeZone europeBerlin = TimeZone.getTimeZone("Europe/Berlin");

    assertThat(TimeZone.getDefault()).isEqualTo(TimeZone.getTimeZone("UTC"));

    timeZone.useTimeZone(europeBerlin);

    assertThat(TimeZone.getDefault()).isEqualTo(europeBerlin);
  }

  @Test
  public void useSystemLocale() {
    assertThat(TimeZone.getDefault()).isEqualTo(TimeZone.getTimeZone("UTC"));

    timeZone.useSystemTimeZone();

    assertThat(TimeZone.getDefault()).isEqualTo(systemTimeZone);
  }

  @Test
  public void utc() {
    assertThat(DefaultTimeZoneRule.utc().getTimeZone()).isEqualTo(TimeZone.getTimeZone("UTC"));
  }

  @Test
  public void etcUTC() {
    assertThat(DefaultTimeZoneRule.etcUTC().getTimeZone())
        .isEqualTo(TimeZone.getTimeZone("Etc/UTC"));
  }

  @Test
  public void forTimeZone() {
    TimeZone europeBerlin = TimeZone.getTimeZone("Europe/Berlin");

    assertThat(DefaultTimeZoneRule.forTimeZone(europeBerlin).getTimeZone()).isEqualTo(europeBerlin);
  }
}
