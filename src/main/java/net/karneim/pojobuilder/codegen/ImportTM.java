package net.karneim.pojobuilder.codegen;

/**
 * @since 3.0
 * @author karneim
 */
public class ImportTM {
    private String text;

    public ImportTM() {
    }

    public ImportTM(String aText) {
        this.text = aText;
    }

    @Override
    public String toString() {
        return text;
    }

}
