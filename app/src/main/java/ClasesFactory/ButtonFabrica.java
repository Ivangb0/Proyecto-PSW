package ClasesFactory;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Button;

public class ButtonFabrica implements Producto {

    public static Button create(Context context, String text) {
        Button button = new Button(context);
        button.setText(text);
        button.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return button;
    }
}
