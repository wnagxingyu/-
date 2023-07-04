<template>
  <div>
    <el-form :model="searchform" style="display: flex">
      <el-form-item label="成员名字">
        <el-input v-model="searchform.membersName" />
      </el-form-item>
      <el-form-item style="margin-left: 10px">
        <el-button type="primary" @click="getData">搜索</el-button>
        <el-button type="danger" @click="add">新增</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column prop="membersId" label="成员ID" />
      <el-table-column prop="familyId" label="家族ID" />
      <el-table-column prop="membersName" label="成员名字" />
      <el-table-column prop="membersSex" label="性别" />
      <el-table-column prop="membersBirthday" label="出生日期" />
      <el-table-column prop="membersDeath" label="死亡日期" />
      <el-table-column prop="updatetime" label="更新时间">
        <template #default="scope">{{
          getTime(scope.row.updatetime)
        }}</template>
      </el-table-column>
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
          label="家族"
          :label-width="formLabelWidth"
          prop="familyId"
        >
          <el-select v-model="form.familyId" placeholder="Select">
            <el-option
              v-for="item in options"
              :key="item.familyId"
              :label="item.familyName"
              :value="item.familyId"
            />
          </el-select>
        </el-form-item>
        <el-form-item
          label="成员名字"
          :label-width="formLabelWidth"
          prop="membersName"
        >
          <el-input v-model="form.membersName" autocomplete="off" />
        </el-form-item>
        <el-form-item label="性别" :label-width="formLabelWidth" prop="membersSex">
          <el-select v-model="form.membersSex" placeholder="请选择">
            <el-option label="male" value="male" />
            <el-option label="female" value="female" />
          </el-select>
        </el-form-item>
        <el-form-item
          label="出生日期"
          :label-width="formLabelWidth"
          prop="membersBirthday"
        >
          <el-input v-model="form.membersBirthday" autocomplete="off" />
        </el-form-item>
        <el-form-item
          label="死亡日期"
          :label-width="formLabelWidth"
          prop="membersDeath"
        >
          <el-input v-model="form.membersDeath" autocomplete="off" />
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
  membersName: "",
});

const formSize = ref("default");
const ruleFormRef = ref<FormInstance>();
let form = reactive({
  membersId: "",
  familyId: "",
  membersName: "",
  membersSex: "",
  membersBirthday: "",
  membersDeath: "",
  updatetime: new Date(),
});
const rules = reactive<FormRules>({
  membersName: [{ required: true, message: "请输入成员名字", trigger: "blur" }],
  membersDeath: [{ required: true, message: "请输入死亡日期", trigger: "blur" }],
  membersBirthday: [{ required: true, message: "请输入出生日期", trigger: "blur" }],
  familyId: [{ required: true, message: "请选择家族", trigger: "change" }],
  membersSex: [
    {
      required: true,
      message: "请选择性别",
      trigger: "change",
    },
  ],
});

const getData = () => {
  axios
    .post("http://localhost:8100/Members/list2", searchform)
    .then((res: any) => {
      tableData.value = res.data;
    });
};

const getFamily = () => {
  axios
    .post("http://localhost:8100/Family/list2", searchform)
    .then((res: any) => {
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
  dialogFormVisible.value = true;
};

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  await formEl.validate((valid, fields) => {
    if (valid) {
      if (!form.membersId) {
        form.updatetime = new Date()
      }
      axios.post(`http://localhost:8100/Members/${!form.membersId?'save':'mod'}`, form).then((res: any) => {
        resetForm(formEl);
        ElMessage.success("新增成功");
        getData();
      }).catch(()=>{
        ElMessage.error("新增失败");
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
  axios.post("http://localhost:8100/Members/delete", data).then((res: any) => {
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
  getFamily()
});
</script>
