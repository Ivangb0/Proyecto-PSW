package ClasesFactory;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class ImageButtonFabrica implements Producto {
    ImageButton imageButton = null;

    public ImageButton create(Context context) {
        imageButton = new ImageButton(context);
        imageButton.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return imageButton;
    }
}
