/*
 * SonarQube XML Plugin
 * Copyright (C) 2010-2021 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package com.sqli.sapcx;

import java.util.ArrayList;
import java.util.List;

import org.sonar.api.config.Configuration;
import org.sonar.api.resources.AbstractLanguage;

/**
 * This class defines the XML language.
 *
 * @author Matthijs Galesloot
 */
public class SapCxItems extends AbstractLanguage {

  /** All the valid xml files suffixes. */
  private static final String[] DEFAULT_SUFFIXES = {"items.xml"};

  /** The xml language key. */
  public static final String KEY = "sapcxitems";

  /** The xml language name */
  private static final String SAPCX_ITEMS_LANGUAGE_NAME = "SAPCX Items";

  public static final String ITEMS_RESOURCE_PATH = "com/sqli/l10n/sapcx/rules/items";
  public static final String REPOSITORY_KEY = "sapcx-items";
  public static final String REPOSITORY_NAME = "SAPCX Items Analyzer";

  public static final String SQLI_WAY_PROFILE_NAME = "SQLI way";
  public static final String SQLI_WAY_PATH = "com/sqli/l10n/sapcx/rules/items/SQLI_way_profile.json";

  private Configuration configuration;

  /**
   * Default constructor.
   *
   * @param configuration configuration to configure this class
   */
  public SapCxItems(Configuration configuration) {
    super(KEY, SAPCX_ITEMS_LANGUAGE_NAME);
    this.configuration = configuration;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String[] getFileSuffixes() {
    String[] suffixes = filterEmptyStrings(configuration.getStringArray(ItemsXmlPlugin.FILE_SUFFIXES_KEY));
    if (suffixes.length == 0) {
      suffixes = SapCxItems.DEFAULT_SUFFIXES;
    }
    return suffixes;
  }

  private static String[] filterEmptyStrings(String[] stringArray) {
    List<String> nonEmptyStrings = new ArrayList<>();
    for (String string : stringArray) {
      if (!string.trim().isEmpty()) {
        nonEmptyStrings.add(string.trim());
      }
    }
    return nonEmptyStrings.toArray(new String[nonEmptyStrings.size()]);
  }
}