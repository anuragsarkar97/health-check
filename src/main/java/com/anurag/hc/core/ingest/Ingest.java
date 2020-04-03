/*
 * Copyright (c) 2020.
 * Aurthor : Anurag Sarkar
 * Please raise an Issue before creating a pull request.
 * Refer HELP for more details.
 *
 *
 */

package com.anurag.hc.core.ingest;

import com.anurag.hc.model.DBType;

public abstract class Ingest {

    protected DBType database;

    public abstract boolean save();

}
