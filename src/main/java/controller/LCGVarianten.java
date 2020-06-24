package controller;

public enum LCGVarianten {
    /**
     * m = 2^31 <br>
     * a = 1103515245 <br>
     * c = 12345 <br>
     * x0 = 12345
     */
    Ansi_C,

    /**
     * m = 2^31 - 1 <br>
     * a = 16807 <br>
     * c = 0 <br>
     * x0 = 1
     */
    MinStandard,

    /**
     * m = 2^31 <br>
     * a = 65539 <br>
     * c = 0 <br>
     * x0 = 1
     */
    RANDU,

    /**
     * m = 2^31 - 1 <br>
     * a = 630360016 <br>
     * c = 0 <br>
     * x0 = 1
     */
    SIMSCRIPT,

    /**
     * m = 2^59 <br>
     * a = 13^13 <br>
     * c = 0 <br>
     * x0 = 123456789
     */
    NAG_LCG,

    /**
     * m = 10^12 - 11 <br>
     * a = 427419669081 <br>
     * c = 0 <br>
     * x0 = 1
     */
    Maple_LCG
}
