<template>
  <div class="inner-link" v-loading="loading" element-loading-text="正在加载页面，请稍候！">
    <iframe
        :id="iframeId"
        style="width: 100%; height: 100%"
        :src="src"
        frameborder="no"
    ></iframe>
  </div>
</template>

<script setup>
import {ref, onMounted} from "vue"

const props = defineProps({
  src: {
    type: String,
    default: "/"
  },
  iframeId: {
    type: String,
    required: true
  }
})

const loading = ref(false)

const handleIframeLoad = () => {
  loading.value = false
}

const handleIframeError = () => {
  loading.value = false
}

onMounted(() => {
  const iframe = document.getElementById(props.iframeId)

  if (!iframe) {
    return
  }

  loading.value = true

  if (iframe.attachEvent) {
    iframe.attachEvent("onload", handleIframeLoad)
    iframe.attachEvent("onerror", handleIframeError)
  } else {
    iframe.onload = handleIframeLoad
    iframe.onerror = handleIframeError
  }
})
</script>

<style scoped>
.inner-link {
  height: calc(100% - 90px);
}
</style>
