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

/**
 * This class defines the XML language.
 *
 * @author Matthijs Galesloot
 */
public class SapCxItemsPreperties {

    /**
     * All the valid items.xml files suffixes.
     */
    public static final String SAPCX_ITEMS_SUFFIXES = "**/*-items.xml";

    public static final String XML_KEY = "xml";

    public static final String ITEMS_RESOURCE_PATH = "com/sqli/l10n/sapcx/rules/items";
    public static final String REPOSITORY_KEY = "sapcx-items";
    public static final String REPOSITORY_NAME = "SAPCX Items Analyzer";

    public static final String SQLI_WAY_PROFILE_NAME = "Sonar way with SAP CX Items rules";
    public static final String SQLI_WAY_PATH = "com/sqli/l10n/sapcx/rules/items/SQLI_way_profile.json";

    public static final String XML_SONAR_WAY = "Sonar way";
}
