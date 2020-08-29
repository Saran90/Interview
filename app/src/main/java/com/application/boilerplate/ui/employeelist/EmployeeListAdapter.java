package com.application.boilerplate.ui.employeelist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.application.boilerplate.R;
import com.application.boilerplate.databinding.ItemEmployeeListBinding;
import com.application.boilerplate.ui.base.BaseViewHolder;
import com.bumptech.glide.Glide;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Saran M S on 8/17/2019.
 */
public class EmployeeListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<EmployeeModel> mEmployeeModelList;

    private EmployeeListAdapterListener mListener;

    public EmployeeListAdapter(List<EmployeeModel> patientListModelList) {
        this.mEmployeeModelList = patientListModelList;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemEmployeeListBinding itemEmployeeListBinding = ItemEmployeeListBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new EmployeeListViewHolder(itemEmployeeListBinding);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return mEmployeeModelList.size();
    }

    public interface EmployeeListAdapterListener {
        void onEmployeeClicked(EmployeeModel employeeModel);
    }

    public void addItems(List<EmployeeModel> patientListModels) {
        mEmployeeModelList.clear();
        mEmployeeModelList.addAll(patientListModels);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mEmployeeModelList.clear();
    }

    public void setListener(EmployeeListAdapterListener listener) {
        this.mListener = listener;
    }

    public class EmployeeListViewHolder extends BaseViewHolder
            implements EmployeeListItemViewModel.EmployeeListItemViewModelListener {

        public ItemEmployeeListBinding mBinding;

        public EmployeeListItemViewModel mEmployeeListItemViewModel;

        public ItemEmployeeListBinding getmBinding() {
            return mBinding;
        }

        public EmployeeListViewHolder(ItemEmployeeListBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            final EmployeeModel employeeModel = mEmployeeModelList.get(position);
            mEmployeeListItemViewModel = new EmployeeListItemViewModel(employeeModel,this);
            mBinding.setViewModel(mEmployeeListItemViewModel);
            Glide.with(mBinding.profileImageView.getContext()).load(employeeModel.getProfileImage()).placeholder(R.drawable.ic_image_placeholder).into(mBinding.profileImageView);
            mBinding.executePendingBindings();
        }

        @Override
        public void onItemClick(EmployeeModel employeeModel) {
            mListener.onEmployeeClicked(employeeModel);
        }
    }

    public void reset(int position){
        notifyItemChanged(position);
    }
}
