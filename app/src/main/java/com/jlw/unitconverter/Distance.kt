package com.jlw.unitconverter

/**
 * An enum class comprising different Distance units.
 */
enum class Distance(val shortCode: String) {

    MILLIMETRE("mm"),
    CENTIMETRE("cm"),
    METRE("m"),
    KILOMETRE("km"),
    MILE("mile"),
    YARD("yard"),
    FOOT("ft"),
    INCH("inch");

    companion object {
        /**
         * Returns the conversion factor as a double based on the distance units.
         *
         * @param primaryDistanceUnit the primary unit
         * @param secondaryDistanceUnit the secondary unit
         * @return the conversion factor of going from primary -> secondary
         */
        fun getConversionFactor(
            primaryDistanceUnit: Distance,
            secondaryDistanceUnit: Distance): Double {
            when (primaryDistanceUnit) {
                MILLIMETRE -> return when (secondaryDistanceUnit) {
                    CENTIMETRE -> 0.1
                    METRE -> 0.001
                    KILOMETRE -> 0.0000001
                    MILE -> 6.2137E-7
                    YARD -> 0.00109361
                    FOOT -> 0.00328084
                    INCH -> 0.0393701
                    else -> 1.0
                }

                CENTIMETRE -> return when (secondaryDistanceUnit) {
                    MILLIMETRE -> 10.0
                    METRE -> 0.01
                    KILOMETRE -> 0.000001
                    MILE -> 6.2137e-6
                    YARD -> 0.0109361
                    FOOT -> 0.0328084
                    INCH -> 0.393701
                    else -> 1.0
                }

                METRE -> return when (secondaryDistanceUnit) {
                    MILLIMETRE -> 1000.0
                    CENTIMETRE -> 100.0
                    KILOMETRE -> 0.001
                    MILE -> 0.000621371
                    YARD -> 1.09361
                    FOOT -> 3.28084
                    INCH -> 39.3701
                    else -> 1.0
                }

                KILOMETRE -> return when (secondaryDistanceUnit) {
                    MILLIMETRE -> 1000000.0
                    CENTIMETRE -> 100000.0
                    METRE -> 1000.0
                    MILE -> 0.621371
                    YARD -> 1093.61
                    FOOT -> 3280.84
                    INCH -> 39370.1
                    else -> 1.0
                }

                MILE -> return when (secondaryDistanceUnit) {
                    MILLIMETRE -> 1.609e+6
                    CENTIMETRE -> 160934.0
                    METRE -> 1609.34
                    KILOMETRE -> 1.60934
                    YARD -> 1760.0
                    FOOT -> 5280.0
                    INCH -> 63360.0
                    else -> 1.0
                }

                YARD -> return when (secondaryDistanceUnit) {
                    MILLIMETRE -> 914.4
                    CENTIMETRE -> 91.44
                    METRE -> 0.9144
                    KILOMETRE -> 0.0009144
                    MILE -> 0.000568182
                    FOOT -> 3.0
                    INCH -> 36.0
                    else -> 1.0
                }

                FOOT -> return when (secondaryDistanceUnit) {
                    MILLIMETRE -> 304.8
                    CENTIMETRE -> 30.48
                    METRE -> 0.3048
                    KILOMETRE -> 0.0003048
                    MILE -> 0.000189394
                    YARD -> 0.333333
                    INCH -> 12.0
                    else -> 1.0
                }

                INCH -> return when (secondaryDistanceUnit) {
                    MILLIMETRE -> 25.4
                    CENTIMETRE -> 2.54
                    METRE -> 0.0254
                    KILOMETRE -> 2.54e-5
                    MILE -> 1.5783e-5
                    YARD -> 0.0277778
                    FOOT -> 0.0833333
                    else -> 1.0
                }
            }
        }
    }
}