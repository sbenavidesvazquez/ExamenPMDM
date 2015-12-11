package com.example.samsung.examenpmdm;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.samsung.examenpmdm.dummy.DummyContent;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;
    private onItemSelectedListener listener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.content);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.details);
            //Instanciamos el Boton
            Button limpiar= (Button) rootView.findViewById(R.id.boton);
            //Se llama al onclicklistener e implementamos el método onclick
            limpiar.setOnClickListener(new View.OnClickListener() {
                //Con esto lo modifica para la version 4.2
                @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
                //En este metodo llamamos a otro que haremos abajo
                @Override
                public void onClick(View v) {
                    Limpieza();
                    //Si esta en vertical, se cerrará sino, no
                    if(rootView==null || !rootView.isInLayout()){
                        getActivity().finish();
                    }else{

                    }

                }
            });
        }
        listener.envio("Ok");
        return rootView;
    }
    //Metodo para dejar vacio el fragment
    public void Limpieza(){
        //instanciamos un textview y lo unimos al del fragment.
        TextView tx= (TextView) getView().findViewById(R.id.item_detail);
        //Sobrescribimos los detalles y que esta vez no escriba nada.
        tx.setText(" ");

    }
    //Creamos una interfaz
    public interface onItemSelectedListener{
        public void envio(String mensaje);
    }

    @Override
    public void onAttach(Activity context){
        super.onAttach(context);

        if(context instanceof onItemSelectedListener){
            listener = (onItemSelectedListener) context;
        }

    }
}
