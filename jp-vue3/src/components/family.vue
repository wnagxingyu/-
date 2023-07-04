<template>
  <div>
    <el-form :model="searchform" style="display: flex">
      <el-form-item label="家族名称">
        <el-input v-model="searchform.familyName" />
      </el-form-item>
      <el-form-item style="margin-left: 10px">
        <el-button type="primary" @click="getData">搜索</el-button>
        <el-button type="danger" @click="add">新增</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column prop="familyId" label="家族ID" />
      <el-table-column prop="familyName" label="家族名称" />
      <el-table-column prop="familyContent" label="内容描述" />
      <el-table-column prop="familyTime" label="创建日期" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button
            type="primary"
            size="small"
            @click.prevent="update(scope.row)"
          >
            编辑
          </el-button>
          <el-button
            type="danger"
            size="small"
            @click.prevent="deleteRow(scope.row)"
          >
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog v-model="dialogFormVisible" title="新增">
      <el-form
        ref="ruleFormRef"
        :model="form"
        :rules="rules"
        label-width="120px"
        class="demo-ruleForm"
        :size="formSize"
        status-icon
      >
        <el-form-item
          label="家族名称"
          :label-width="formLabelWidth"
          prop="familyName"
        >
          <el-input v-model="form.familyName" autocomplete="off" />
        </el-form-item>
        <el-form-item
          label="内容描述"
          :label-width="familyContent"
          prop="familyContent"
        >
          <el-input v-model="form.familyContent" autocomplete="off" />
        </el-form-item>
        <el-form-item label="创建日期" :label-width="familyTime" prop="familyTime">
          <el-input v-model="form.familyTime" autocomplete="off" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm(ruleFormRef)">
            提交
          </el-button>
          <el-button @click="resetForm(ruleFormRef)">关闭</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import { onMounted, ref, reactive } from "vue";
import axios from "axios";
import type { FormInstance, FormRules, ElMessage } from "element-plus";

const tableData = ref([]);

const dialogFormVisible = ref(false);
const formLabelWidth = "120px";

const searchform = reactive({
  familyName: "",
});

const formSize = ref("default");
const ruleFormRef = ref<FormInstance>();
let form = reactive({
  familyName: "",
  familyContent: "",
  familyTime: "",
  deleted: 0,
  id: 0,
  updatetime: new Date()
});
const rules = reactive<FormRules>({
  familyName: [{ required: true, message: "请输入家族名称", trigger: "blur" }],
  familyContent: [{ required: true, message: "请输入内容描述", trigger: "blur" }],
  familyTime: [{ required: true, message: "请输入创建日期", trigger: "blur" }]
});

const getData = () => {
  axios.post("http://localhost:8100/Family/list2", searchform).then((res: any) => {
    tableData.value = res.data;
  });
};

const add = () => {
  for (const key in form) {
    if (Object.prototype.hasOwnProperty.call(form, key)) {
      form[key] = null;
    }
  }
  dialogFormVisible.value = true;
};

const update = (data: object) => {
  form = Object.assign(form, data);
  dialogFormVisible.value = true;
};

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  await formEl.validate((valid, fields) => {
    if (valid) {
      if (!form.familyId) {
        form.updatetime = new Date()
        form.id = JSON.parse(localStorage.getItem("user") || "").id
      }
      axios
        .post(`http://localhost:8100/Family/${!form.familyId?'save':'mod'}`, form)
      .then((res: any) => {
        resetForm(formEl)
          ElMessage.success("操作成功");
          getData();
      }).catch(()=>{
        ElMessage.error("操作失败");
      })
    } else {
      console.log("error submit!", fields);
    }
  });
};

const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.resetFields();
  dialogFormVisible.value = false;
};

const deleteRow = (data: object) => {
  axios.post("http://localhost:8100/Family/delete", data).then((res: any) => {
    ElMessage.success("删除成功");
      getData();
  }).catch(()=>{
        ElMessage.error("删除失败");
   })
};

const getTime = (time)=>{
    if(time==null){
        return ""
    }
    return time.substring(0,10)+ " "+time.substring(11,19)
}


onMounted(() => {
  getData();
});
</script>
