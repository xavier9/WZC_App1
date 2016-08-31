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
 * This code was generated by https://github.com/google/apis-client-generator/
 * (build: 2016-07-08 17:28:43 UTC)
 * on 2016-08-31 at 16:19:54 UTC 
 * Modify at your own risk.
 */

package com.ehb.xavier.api.rolEndpoint.model;

/**
 * Model definition for Rol.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the rolEndpoint. For a detailed explanation see:
 * <a href="https://developers.google.com/api-client-library/java/google-http-java-client/json">https://developers.google.com/api-client-library/java/google-http-java-client/json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Rol extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long id;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer menu;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String naam;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getId() {
    return id;
  }

  /**
   * @param id id or {@code null} for none
   */
  public Rol setId(java.lang.Long id) {
    this.id = id;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getMenu() {
    return menu;
  }

  /**
   * @param menu menu or {@code null} for none
   */
  public Rol setMenu(java.lang.Integer menu) {
    this.menu = menu;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getNaam() {
    return naam;
  }

  /**
   * @param naam naam or {@code null} for none
   */
  public Rol setNaam(java.lang.String naam) {
    this.naam = naam;
    return this;
  }

  @Override
  public Rol set(String fieldName, Object value) {
    return (Rol) super.set(fieldName, value);
  }

  @Override
  public Rol clone() {
    return (Rol) super.clone();
  }

}