package com.application.bris.ikurma_nos_konsumer.page_aom.view.prapen.d3_confirm_validasi_engine.akseptasi_pendapatan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.model.request.prapen.SubAkseptasiPendapatan;
import com.application.bris.ikurma_nos_konsumer.databinding.ItemAkseptasiPendapatanBinding;
import com.application.bris.ikurma_nos_konsumer.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma_nos_konsumer.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma_nos_konsumer.page_aom.model.MGenericModel;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.NumberTextWatcherCanNolForThousand;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class AkseptasiPendapatanAdapter extends RecyclerView.Adapter<AkseptasiPendapatanAdapter.MenuViewHolder> implements GenericListenerOnSelect {

    private final List<SubAkseptasiPendapatan> data;
    private final Context context;
    private final List<MGenericModel> komponen;
    private final List<MGenericModel> treatment;
    private Integer posisi = 0;
    private ItemAkseptasiPendapatanBinding binding;
    private MenuViewHolder holderdata;
    private AkseptasiPendapatanAdapter.CallbackInterface mCallback;

    public interface CallbackInterface {
        void onHandleSelection(int position, SubAkseptasiPendapatan data);
    }


    public AkseptasiPendapatanAdapter(Context context, List<SubAkseptasiPendapatan> mdata, List<MGenericModel> picklist, List<MGenericModel> treatment) {
        this.context = context;
        this.data = mdata;
        this.komponen = picklist;
        this.treatment = treatment;
        try {
            mCallback = (CallbackInterface) context;
        } catch (ClassCastException ex) {
            AppUtil.logSecure("eroor", ex.getMessage());
        }
    }

    @NonNull
    @Override
    public AkseptasiPendapatanAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        binding = ItemAkseptasiPendapatanBinding.inflate(layoutInflater, parent, false);
        View view = binding.getRoot();
        return new AkseptasiPendapatanAdapter.MenuViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        final SubAkseptasiPendapatan datas = data.get(position);
        holder.etinputnominal.addTextChangedListener(new NumberTextWatcherCanNolForThousand(holder.etinputnominal));
        holder.etsetelahakseptasi.addTextChangedListener(new NumberTextWatcherCanNolForThousand(holder.etsetelahakseptasi));
        List<String> title = Arrays.asList(context.getResources().getStringArray(R.array.urutan_nama_dokumen));
        holder.ettreatmentpendapatan.setText(datas.getStatus_Payroll());
        holder.etsetelahakseptasi.setText(String.valueOf(datas.getNilaiSetelahAkseptasi().setScale(2,RoundingMode.HALF_EVEN)));
        holder.etpilihanakseptasipendapatan.setText(datas.getKeterangan());
        for (MGenericModel d : komponen) {
            if (d.getDESC().equals(datas.getKeterangan()))
                holder.etparam.setText(String.valueOf(d.getNilai()));
                holder.etpersenAkseptasi.setText(String.valueOf(new BigDecimal(d.getNilai()).multiply(new BigDecimal(100)).setScale(0,RoundingMode.HALF_EVEN)));
        }
        holder.etinputnominal.setText(String.valueOf(datas.getPendapatan_Tercermin().setScale(0,RoundingMode.HALF_EVEN)));
        holder.tvTitle.setText("Komponen Akseptasi " + title.get(position));
        onclickbutton(holder, position);
        onendiconclick(holder, position);
        disabledText(holder);
        onchangeData(holder, datas, position);
    }

    private void disabledText(@NonNull MenuViewHolder holder) {
        holder.tftreatmentpendapatan.setFocusable(false);
        holder.ettreatmentpendapatan.setFocusable(false);
        holder.tfpilihanakseptasipendapatan.setFocusable(false);
        holder.etpilihanakseptasipendapatan.setFocusable(false);
        holder.etsetelahakseptasi.setFocusable(false);
        holder.tfsetelahakseptasi.setFocusable(false);
        holder.etpersenAkseptasi.setFocusable(false);
    }


    private void onclickbutton(@NonNull MenuViewHolder holder, int position) {
        holder.tftreatmentpendapatan.setOnClickListener(
                v -> {
                    holderdata = holder;
                    posisi = position;
                    DialogGenericDataFromService.display(((FragmentActivity) context).getSupportFragmentManager(), holder.tftreatmentpendapatan.getLabelText(), treatment, AkseptasiPendapatanAdapter.this);
                });
        holder.ettreatmentpendapatan.setOnClickListener(
                v -> {
                    holderdata = holder;
                    posisi = position;
                    DialogGenericDataFromService.display(((FragmentActivity) context).getSupportFragmentManager(), holder.tftreatmentpendapatan.getLabelText(), treatment, AkseptasiPendapatanAdapter.this);
                });
        holder.tfpilihanakseptasipendapatan.setOnClickListener(
                v -> {
                    holderdata = holder;
                    posisi = position;
                    DialogGenericDataFromService.display(((FragmentActivity) context).getSupportFragmentManager(), holder.tfpilihanakseptasipendapatan.getLabelText(), komponen, AkseptasiPendapatanAdapter.this);
                });
        holder.etpilihanakseptasipendapatan.setOnClickListener(
                v -> {
                    holderdata = holder;
                    posisi = position;
                    DialogGenericDataFromService.display(((FragmentActivity) context).getSupportFragmentManager(), holder.tfpilihanakseptasipendapatan.getLabelText(), komponen, AkseptasiPendapatanAdapter.this);
                });
    }


    private void onchangeData(@NonNull MenuViewHolder holder, SubAkseptasiPendapatan data, int position) {

        holder.etinputnominal.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!holder.etpilihanakseptasipendapatan.getText().toString().isEmpty() && !s.toString().equals("")) {
                    BigDecimal total;
                    total = new BigDecimal(String.valueOf(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(s.toString())).multiply(new BigDecimal(holder.etparam.getText().toString()))));
                    holder.etsetelahakseptasi.setText(String.valueOf(total.setScale(2, RoundingMode.HALF_EVEN)));
                    data.setPendapatan_Tercermin(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(holder.etinputnominal.getText().toString())));
                    data.setNilaiSetelahAkseptasi(total);
                }
            }
        });
        holder.ettreatmentpendapatan.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                callbackData(position);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        holder.etpilihanakseptasipendapatan.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                data.setKeterangan(s.toString());
                callbackData(position);
                if (!holder.etpilihanakseptasipendapatan.getText().toString().isEmpty() && !holder.etinputnominal.getText().toString().equals("")) {
                    BigDecimal total;
                    total = new BigDecimal(String.valueOf(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(holder.etinputnominal.getText().toString())).multiply(new BigDecimal(holder.etparam.getText().toString())))).setScale(2,RoundingMode.HALF_EVEN);
                    holder.etsetelahakseptasi.setText(String.valueOf(total));
                    data.setPendapatan_Tercermin(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(holder.etinputnominal.getText().toString())));
                    data.setNilaiSetelahAkseptasi(total);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

    }


    private void onendiconclick(@NonNull MenuViewHolder holder, int position) {
        holder.tftreatmentpendapatan.getEndIconImageButton().setOnClickListener(
                v -> {
                    holderdata = holder;
                    posisi = position;
                    DialogGenericDataFromService.display(((FragmentActivity) context).getSupportFragmentManager(), holder.tftreatmentpendapatan.getLabelText(), treatment, AkseptasiPendapatanAdapter.this);
                });
        holder.tfpilihanakseptasipendapatan.getEndIconImageButton().setOnClickListener(
                v -> {
                    holderdata = holder;
                    posisi = position;
                    DialogGenericDataFromService.display(((FragmentActivity) context).getSupportFragmentManager(), holder.tfpilihanakseptasipendapatan.getLabelText(), komponen, AkseptasiPendapatanAdapter.this);
                });
    }

    private void callbackData(@NonNull Integer position) {
        if (mCallback != null) {
            mCallback.onHandleSelection(position,
                    data.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onSelect(String title, MGenericModel data) {
        if (title.equalsIgnoreCase(holderdata.tftreatmentpendapatan.getLabelText())) {

            this.data.get(posisi).setStatus_Payroll(data.getDESC());
            holderdata.ettreatmentpendapatan.setText(data.getDESC());
        } else if (title.equalsIgnoreCase(holderdata.tfpilihanakseptasipendapatan.getLabelText())) {
            this.data.get(posisi).setPendapatanTunjanganId(Long.parseLong(data.getNAMA()));
            this.data.get(posisi).setPendapatanId(Long.parseLong(data.getID()));
            holderdata.etparam.setText(String.valueOf(data.getNilai()));
            holderdata.etpersenAkseptasi.setText(String.valueOf(new BigDecimal(data.getNilai()).multiply(new BigDecimal(100)).setScale(0,RoundingMode.HALF_EVEN)));
            holderdata.etpilihanakseptasipendapatan.setText(data.getDESC());
        }
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        ExtendedEditText etpilihanakseptasipendapatan, etinputnominal, etsetelahakseptasi, ettreatmentpendapatan, etparam,etpersenAkseptasi;
        TextFieldBoxes tfpilihanakseptasipendapatan, tftreatmentpendapatan,tfsetelahakseptasi;
        TextView tvTitle;

        public MenuViewHolder(View itemView) {
            super(itemView);
            etpilihanakseptasipendapatan = binding.etPilihanAkseptasiPendapatan;
            etinputnominal = binding.etInputNominal;
            etsetelahakseptasi = binding.etSetelahAkseptasi;
            ettreatmentpendapatan = binding.etTreatmentPendapatan;
            tfsetelahakseptasi = binding.tfSetelahAkseptasi;
            tfpilihanakseptasipendapatan = binding.tfPilihanAkseptasiPendapatan;
            tftreatmentpendapatan = binding.tfTreatmentPendapatan;
            tvTitle = binding.tvTitle;
            etparam = binding.etParameter;
            etpersenAkseptasi=binding.etPersentaseAkseptasi;

        }
    }
}
