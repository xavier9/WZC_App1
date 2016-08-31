/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2015-01-14 17:53:03 UTC)
 * on 2015-02-15 at 11:21:02 UTC 
 * Modify at your own risk.
 */

package com.ehb.xavier.api.eventEndpoint.model;

/**
 * Model definition for Locale.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the eventEndpoint. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Locale extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String country;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String displayCountry;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String displayLanguage;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String displayName;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String displayScript;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String displayVariant;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<java.lang.String> extensionKeys;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String iso3Country;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String iso3Language;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String language;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String script;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<java.lang.String> unicodeLocaleAttributes;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<java.lang.String> unicodeLocaleKeys;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String variant;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getCountry() {
    return country;
  }

  /**
   * @param country country or {@code null} for none
   */
  public Locale setCountry(java.lang.String country) {
    this.country = country;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getDisplayCountry() {
    return displayCountry;
  }

  /**
   * @param displayCountry displayCountry or {@code null} for none
   */
  public Locale setDisplayCountry(java.lang.String displayCountry) {
    this.displayCountry = displayCountry;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getDisplayLanguage() {
    return displayLanguage;
  }

  /**
   * @param displayLanguage displayLanguage or {@code null} for none
   */
  public Locale setDisplayLanguage(java.lang.String displayLanguage) {
    this.displayLanguage = displayLanguage;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getDisplayName() {
    return displayName;
  }

  /**
   * @param displayName displayName or {@code null} for none
   */
  public Locale setDisplayName(java.lang.String displayName) {
    this.displayName = displayName;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getDisplayScript() {
    return displayScript;
  }

  /**
   * @param displayScript displayScript or {@code null} for none
   */
  public Locale setDisplayScript(java.lang.String displayScript) {
    this.displayScript = displayScript;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getDisplayVariant() {
    return displayVariant;
  }

  /**
   * @param displayVariant displayVariant or {@code null} for none
   */
  public Locale setDisplayVariant(java.lang.String displayVariant) {
    this.displayVariant = displayVariant;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<java.lang.String> getExtensionKeys() {
    return extensionKeys;
  }

  /**
   * @param extensionKeys extensionKeys or {@code null} for none
   */
  public Locale setExtensionKeys(java.util.List<java.lang.String> extensionKeys) {
    this.extensionKeys = extensionKeys;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getIso3Country() {
    return iso3Country;
  }

  /**
   * @param iso3Country iso3Country or {@code null} for none
   */
  public Locale setIso3Country(java.lang.String iso3Country) {
    this.iso3Country = iso3Country;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getIso3Language() {
    return iso3Language;
  }

  /**
   * @param iso3Language iso3Language or {@code null} for none
   */
  public Locale setIso3Language(java.lang.String iso3Language) {
    this.iso3Language = iso3Language;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getLanguage() {
    return language;
  }

  /**
   * @param language language or {@code null} for none
   */
  public Locale setLanguage(java.lang.String language) {
    this.language = language;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getScript() {
    return script;
  }

  /**
   * @param script script or {@code null} for none
   */
  public Locale setScript(java.lang.String script) {
    this.script = script;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<java.lang.String> getUnicodeLocaleAttributes() {
    return unicodeLocaleAttributes;
  }

  /**
   * @param unicodeLocaleAttributes unicodeLocaleAttributes or {@code null} for none
   */
  public Locale setUnicodeLocaleAttributes(java.util.List<java.lang.String> unicodeLocaleAttributes) {
    this.unicodeLocaleAttributes = unicodeLocaleAttributes;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<java.lang.String> getUnicodeLocaleKeys() {
    return unicodeLocaleKeys;
  }

  /**
   * @param unicodeLocaleKeys unicodeLocaleKeys or {@code null} for none
   */
  public Locale setUnicodeLocaleKeys(java.util.List<java.lang.String> unicodeLocaleKeys) {
    this.unicodeLocaleKeys = unicodeLocaleKeys;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getVariant() {
    return variant;
  }

  /**
   * @param variant variant or {@code null} for none
   */
  public Locale setVariant(java.lang.String variant) {
    this.variant = variant;
    return this;
  }

  @Override
  public Locale set(String fieldName, Object value) {
    return (Locale) super.set(fieldName, value);
  }

  @Override
  public Locale clone() {
    return (Locale) super.clone();
  }

}
