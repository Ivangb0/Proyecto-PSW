package ClasesFactory;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class ViewFactory {
    public static Button createButton(Context context, String text) {
        Button button = new Button(context);
        button.setText(text);
        button.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return button;
    }

    public ImageButton createImageButton(Context context) {
        ImageButton imageButton = new ImageButton(context);
        imageButton.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return imageButton;
    }
}





