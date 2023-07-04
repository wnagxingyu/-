<template>
  <div>
    <el-form :model="searchform" style="display: flex">
      <el-form-item label="类型">
        <el-input v-model="searchform.thingsType" />
      </el-form-item>
      <el-form-item style="margin-left: 10px">
        <el-button type="primary" @click="getData">搜索</el-button>
        <el-button type="danger" @click="add">新增</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column prop="membersId" label="成员ID" />
      <el-table-column prop="thingsTime" label="事迹时间" />
      <el-table-column prop="thingsType" label="类型" />
      <el-table-column prop="thingsContent" label="内容" />
      <el-table-column prop="thingsAccessory" label="奖励" />
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
          label="成员"
          :label-width="formLabelWidth"
          prop="membersId"
        >
          <el-select v-model="form.membersId" :disabled="form.id>0" placeholder="Select">
            <el-option
              v-for="item in options"
              :key="item.membersId"
              :label="item.membersName"
              :value="item.membersId"
            />
          </el-select>
        </el-form-item>
        <el-form-item
          label="事迹时间"
          :label-width="formLabelWidth"
          prop="thingsTime"
        >
          <el-input v-model="form.thingsTime" autocomplete="off" />
        </el-form-item>
        <el-form-item
          label="类型"
          :label-width="formLabelWidth"
          prop="thingsType"
        >
          <el-input v-model="form.thingsType" autocomplete="off" />
        </el-form-item>
      
        <el-form-item label="内容" :label-width="formLabelWidth" prop="thingsContent">
          <el-input v-model="form.thingsContent" autocomplete="off" />
        </el-form-item>
        <el-form-item label="奖励" :label-width="formLabelWidth" prop="thingsAccessory">
          <el-input v-model="form.thingsAccessory" autocomplete="off" />
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

const options = ref([]);

const dialogFormVisible = ref(false);

const formLabelWidth = "140px";

const searchform = reactive({
  thingsType: ""
});

const formSize = ref("default");
const ruleFormRef = ref<FormInstance>();
let form = reactive({
  membersId: null,
  thingsTime: "",
  thingsContent: "",
  thingsType: "",
  thingsAccessory: "",
  updatetime: new Date(),
  deleted: 0,
  id: 0
});
const rules = reactive<FormRules>({
  thingsTime: [{ required: true, message: "请输入", trigger: "blur" }],
  thingsContent: [{ required: true, message: "请输入", trigger: "blur" }],
  thingsType: [{ required: true, message: "请输入", trigger: "blur" }],
  thingsAccessory: [{ required: true, message: "请输入", trigger: "blur" }],
  membersId: [
    {
      required: true,
      message: "请选择",
      trigger: "change",
    },
  ],
});

const getData = () => {
  axios
    .post("http://localhost:8100/Things/list2", searchform)
    .then((res: any) => {
      tableData.value = res.data;
    });
};

const getOption = () => {
  axios.post("http://localhost:8100/Members/list2", {}).then((res: any) => {
    options.value = res.data;
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
  form.id = 1
  dialogFormVisible.value = true;
};

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  await formEl.validate((valid, fields) => {
    if (valid) {
      if (form.id<1) {
        form.updatetime = new Date()
      }
      axios
        .post(`http://localhost:8100/Things/${form.id<1?'save':'mod'}`, form)
        .then((res: any) => {
          resetForm(formEl);
          ElMessage.success("操作成功");
          getData();
        })
        .catch(() => {
          ElMessage.success("操作失败");
        });
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
  axios.post("http://localhost:8100/Things/delete", data).then((res: any) => {
    ElMessage.success("删除成功");
    getData();
  });
};

const getTime = (time) => {
  if (time == null) {
    return "";
  }
  return time.substring(0, 10) + " " + time.substring(11, 19);
};

onMounted(() => {
  getData();
  getOption()
});
</script>
