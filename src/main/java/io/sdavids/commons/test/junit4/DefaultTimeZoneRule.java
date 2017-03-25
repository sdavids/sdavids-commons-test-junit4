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

import static java.util.Objects.requireNonNull;

import com.google.common.annotations.VisibleForTesting;
import java.util.TimeZone;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

/**
 * The {@code DefaultTimeZoneRule} rule allows one to run a test in a specific time zone.
 *
 * <h3 id="usage">Usage</h3>
 *
 * <pre><code>
 * public class SimpleDefaultTimeZoneRuleTest {
 *
 *     &#64;Rule
 *     public DefaultTimeZoneRule timeZone = DefaultTimeZoneRule.utc();
 *
 *     &#64;Test
 *     public void runInUtcTimeZone() {
 *         // TimeZone.getDefault() &#8658; UTC
 *     }
 *
 *     &#64;Test
 *     public void runInEuropeBerlinTimeZone() {
 *         timeZone.useTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
 *
 *         // TimeZone.getDefault() &#8658; Europe/Berlin
 *     }
 * }
 * </code></pre>
 *
 * @since 1.0
 */
@SuppressWarnings("UseOfObsoleteDateTimeApi")
public final class DefaultTimeZoneRule extends TestWatcher {

  /**
   * Creates a new rule in the UTC time zone ("UTC").
   *
   * @return the rule
   * @since 1.0
   */
  public static DefaultTimeZoneRule utc() {
    return new DefaultTimeZoneRule(TimeZone.getTimeZone("UTC"));
  }

  /**
   * Creates a new rule in the UTC time zone ("Etc/UTC").
   *
   * @return the rule
   * @since 1.0
   */
  public static DefaultTimeZoneRule etcUTC() {
    return new DefaultTimeZoneRule(TimeZone.getTimeZone("Etc/UTC"));
  }

  /**
   * Creates a new rule for the specified time zone.
   *
   * @param timeZone the time zone, not null
   * @return the rule
   * @since 1.0
   */
  public static DefaultTimeZoneRule forTimeZone(TimeZone timeZone) {
    return new DefaultTimeZoneRule(requireNonNull(timeZone, "timeZone"));
  }

  private final TimeZone timeZone;

  private TimeZone systemTimeZone;

  private DefaultTimeZoneRule(TimeZone timeZone) {
    this.timeZone = timeZone;
  }

  /**
   * Sets the test method's time zone to the specified one.
   *
   * @param timeZone the time zone, not null
   * @since 1.0
   */
  public void useTimeZone(TimeZone timeZone) {
    TimeZone.setDefault(requireNonNull(timeZone, "timeZone"));
  }

  /**
   * Changes the test method's time zone to the system's time zone.
   *
   * @since 1.0
   */
  public void useSystemTimeZone() {
    TimeZone.setDefault(systemTimeZone);
  }

  @VisibleForTesting
  TimeZone getTimeZone() {
    return timeZone;
  }

  @Override
  protected void starting(Description description) {
    systemTimeZone = TimeZone.getDefault();

    TimeZone.setDefault(timeZone);
  }

  @Override
  protected void finished(Description description) {
    TimeZone.setDefault(systemTimeZone);
  }
}
