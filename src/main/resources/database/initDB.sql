CREATE TABLE IF NOT EXISTS "public"."cars" (
    "registration_id"     TEXT,
    "vehicle_brand"       TEXT,
    "color"               TEXT,
    "year_of_manufacture" TEXT,
    "creation_date"       TEXT NOT NULL,
    PRIMARY KEY ("registration_id")

);